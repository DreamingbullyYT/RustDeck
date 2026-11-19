//! NativeActivity entry point. Android calls `ANativeActivity_onCreate` in the library named by
//! the manifest (`rustdeck_android`). We forward it to the same symbol in the engine library:
//! first <internal data>/payloads/librustdeck_engine.so (downloaded, newer), else the bundled
//! librustdeck_engine_bundled.so.

use std::ffi::{c_char, c_void, CStr, CString};

#[repr(C)]
pub struct ANativeActivity {
    callbacks: *mut c_void,
    vm: *mut c_void,
    env: *mut c_void,
    clazz: *mut c_void,
    internal_data_path: *const c_char,
}

#[link(name = "log")]
unsafe extern "C" {
    fn __android_log_write(prio: i32, tag: *const c_char, text: *const c_char) -> i32;
}

fn log(text: &str) {
    let tag = CString::new("RustDeck").unwrap();
    let text = CString::new(text.replace('\0', " ")).unwrap();
    unsafe { __android_log_write(4, tag.as_ptr(), text.as_ptr()) };
}

type OnCreate = unsafe extern "C" fn(*mut ANativeActivity, *mut c_void, usize);

unsafe fn open(path: &str) -> Option<OnCreate> {
    let c = CString::new(path).ok()?;
    let h = unsafe { libc::dlopen(c.as_ptr(), libc::RTLD_NOW | libc::RTLD_LOCAL) };
    if h.is_null() {
        let err = unsafe { libc::dlerror() };
        let msg = if err.is_null() { "unknown".into() } else { unsafe { CStr::from_ptr(err) }.to_string_lossy().into_owned() };
        log(&format!("loader: dlopen {path} failed: {msg}"));
        return None;
    }
    let sym = unsafe { libc::dlsym(h, c"ANativeActivity_onCreate".as_ptr()) };
    if sym.is_null() {
        log(&format!("loader: {path} has no ANativeActivity_onCreate"));
        return None;
    }
    log(&format!("loader: using {path}"));
    Some(unsafe { std::mem::transmute::<*mut c_void, OnCreate>(sym) })
}

#[unsafe(no_mangle)]
pub unsafe extern "C" fn ANativeActivity_onCreate(
    activity: *mut ANativeActivity,
    saved_state: *mut c_void,
    saved_state_size: usize,
) {
    let mut entry = None;
    let internal = unsafe { (*activity).internal_data_path };
    if !internal.is_null() {
        let dir = unsafe { CStr::from_ptr(internal) }.to_string_lossy().into_owned();
        let downloaded = format!("{dir}/payloads/librustdeck_engine.so");
        if std::path::Path::new(&downloaded).exists() {
            entry = unsafe { open(&downloaded) };
        }
    }
    if entry.is_none() {
        entry = unsafe { open("librustdeck_engine_bundled.so") };
    }
    match entry {
        Some(f) => unsafe { f(activity, saved_state, saved_state_size) },
        None => log("loader: no engine could be loaded"),
    }
}
