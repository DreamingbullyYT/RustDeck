//! RustDeck's Android shell. `android_main` is what NativeActivity calls: it points stdout and
//! stderr at a log file, tells the engine its `AndroidApp`, and runs upstream's `horizon_game::main`.
//! Log file: <app external files dir>/rustdeck.log (and logcat, tag "RustDeck").

use std::ffi::CString;
use std::fs::OpenOptions;
use std::os::fd::AsRawFd;
use std::panic;
use std::path::PathBuf;
use winit::platform::android::activity::AndroidApp;

#[link(name = "log")]
unsafe extern "C" {
    fn __android_log_write(prio: i32, tag: *const libc::c_char, text: *const libc::c_char) -> i32;
}

fn logcat(text: &str) {
    let tag = CString::new("RustDeck").unwrap();
    let text = CString::new(text.replace('\0', " ")).unwrap();
    // 4 = ANDROID_LOG_INFO
    unsafe { __android_log_write(4, tag.as_ptr(), text.as_ptr()) };
}

fn say(text: &str) {
    logcat(text);
    eprintln!("{text}");
}

#[unsafe(no_mangle)]
fn android_main(app: AndroidApp) {
    let internal = app.internal_data_path().unwrap_or_else(|| PathBuf::from("/data/local/tmp"));
    let external = app.external_data_path().unwrap_or_else(|| internal.clone());
    let _ = std::fs::create_dir_all(&external);
    let log_path = external.join("rustdeck.log");
    if let Ok(file) = OpenOptions::new().create(true).write(true).truncate(true).open(&log_path) {
        let fd = file.as_raw_fd();
        unsafe {
            libc::dup2(fd, 1);
            libc::dup2(fd, 2);
        }
        std::mem::forget(file);
    }
    for key in ["HOME", "XDG_DATA_HOME", "XDG_CONFIG_HOME", "XDG_CACHE_HOME", "XDG_STATE_HOME"] {
        unsafe { std::env::set_var(key, &internal) };
    }
    panic::set_hook(Box::new(|info| {
        say(&format!("PANIC: {info}\n{}", std::backtrace::Backtrace::force_capture()));
    }));
    say(&format!(
        "RustDeck shell started. log={} data={}",
        log_path.display(),
        internal.display()
    ));
    horizon_client::android_app::set_app(app);
    let result = panic::catch_unwind(horizon_game::main);
    say(&format!("horizon_game::main returned, panicked={}", result.is_err()));
}
