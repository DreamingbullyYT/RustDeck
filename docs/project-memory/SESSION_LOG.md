# Session Log (append-only)

This file is chronological and append-only. Never rewrite or delete earlier session entries. Add a new entry at the end for each meaningful work session. The validator rejects deletion of existing non-empty journal lines in pull requests and pushes when the base commit is available.

## S-2026-11-18-01 — Bootstrap PMCS
Date: 2026-11-18
Commit: pending
Goal: Start RustDeck from zero with the PMCS memory system from Rusty-survival-.
Changes:
- Added docs/project-memory/ (12 files), validator, regression tests and CI workflow.
- Replaced PARITY_MATRIX and REUSE_MATRIX with UPSTREAM_SYNC.
Verification:
- Local: `python scripts/validate_project_memory.py` PASSED; `python -m unittest discover -s tests` ran 4 tests, OK.
- GitHub Actions run: pending.
Limitations:
- Nothing beyond PMCS exists yet; launcher scope and stack are UNDECIDED.

## S-2026-11-18-02 — Track upstream main, official-launcher UI
Date: 2026-11-18
Commit: pending
Goal: Record the user's decisions: follow upstream `main` with no pinning; UI mirrors the official Minecraft launcher, mobile friendly.
Changes:
- Added D-005 and D-006 and updated handoff, state, roadmap, architecture and upstream-sync docs.
- Validator marker "Pinned upstream" changed to "Tracked upstream".
Verification:
- Local: validator PASSED; 4 regression tests OK.
Limitations:
- GitHub Actions run still pending. No launcher code yet.

## S-2026-11-18-03 — Auto-update decision
Date: 2026-11-18
Commit: pending
Goal: Record that engine, launcher core and UI update automatically from this repo, and the APK only changes for new permissions.
Changes:
- Added D-007 and I-003; updated architecture, state, roadmap and handoff.
Verification:
- See validator and test run recorded in CHANGELOG C-2026-11-18-03.
Limitations:
- Design only; no updater exists and nothing was tested on a device.

## S-2026-11-18-04 — No update channels
Date: 2026-11-18
Commit: pending
Goal: Record that there are no release channels; the newest build is installed.
Changes:
- Added D-008; updated architecture and state docs.
Verification:
- Local: validator PASSED; tests OK.
Limitations:
- Design only; no updater exists.

## S-2026-11-18-05 — Correct D-008 (release and nightly)
Date: 2026-11-18
Commit: pending
Goal: Fix the previous entry: the user wants release and nightly channels.
Changes:
- Rewrote D-008; updated architecture and state docs.
Verification:
- Local: validator PASSED; tests OK.
Limitations:
- Design only; no nightly workflow or updater exists.

## S-2026-11-18-06 — Correct D-008 trigger
Date: 2026-11-18
Commit: pending
Goal: Fix the build trigger: build only when upstream has a new release or nightly.
Changes:
- Rewrote D-008; updated architecture and state docs.
Verification:
- Local: validator PASSED; tests OK.
Limitations:
- Design only; no CI build workflow or updater exists.

## S-2026-11-18-07 — Upstream investigation
Date: 2026-11-18
Commit: pending
Goal: Learn how upstream Horizon publishes builds and what its launcher is.
Changes:
- Recorded observed upstream facts in UPSTREAM_SYNC.md; added I-004 and I-005.
Verification:
- Validator PASSED locally. Findings come from reading upstream at e3d4dc5; nothing was built or run.
Limitations:
- Single snapshot; no Android feasibility check yet.

## S-2026-11-18-08 — Release/nightly meaning and UI direction
Date: 2026-11-18
Commit: pending
Goal: Record the user's answers on release vs nightly and the UI.
Changes:
- Refined D-008, added D-009, resolved I-005.
Verification:
- Validator PASSED locally.
Limitations:
- Decisions only; no code.

## S-2026-11-18-09 — Android status upstream, Compose decision
Date: 2026-11-18
Commit: pending
Goal: Check whether Horizon's client can run on Android and record the UI technology.
Changes:
- Recorded upstream Android status and launcher CLI; added D-010 and I-006; updated I-004.
Verification:
- Validator PASSED locally. Nothing built or run; findings are from source and docs at e3d4dc5.
Limitations:
- Android feasibility still unproven.

## S-2026-11-18-10 — Java bundled for the prototype
Date: 2026-11-18
Commit: pending
Goal: Record the Java-in-APK direction before starting the Android client test.
Changes:
- Added D-011; updated I-006 and next actions.
Verification:
- Validator PASSED locally.
Limitations:
- Decision only; nothing tested.

## S-2026-11-18-11 — Android client probe workflow
Date: 2026-11-18
Commit: pending
Goal: Start the Android client test with a CI compile check of upstream Horizon.
Changes:
- Added the manual workflow android-client-probe.yml; closed I-001.
Verification:
- Validator PASSED locally. Workflow run: pending (result recorded in the next entry).
Limitations:
- Compile check only.

## S-2026-11-18-12 — Android client probe results
Date: 2026-11-18
Commit: pending
Goal: Run the Android compile probe (S-2026-11-18-11) and read its result.
Changes:
- Ran workflow android-client-probe.yml twice against upstream main with Rust 1.100.0 and NDK 30.0.16248370 on ubuntu-latest (target aarch64-linux-android, `cargo check`).
- Probe workflow got a probe-only step enabling winit's `android-native-activity` feature (patch applied to the CI checkout only).
Verification:
- Run 44375047842 (commit a22927c): horizon-client-render and horizon-client both FAILED in `android-activity` (neither "game-activity" nor "native-activity" feature enabled).
- Run 44375791519 (commit 69d958b, native-activity enabled): both FAILED again, now in `arboard` (the clipboard crate has no Android backend). arboard is used only in crates/horizon-client-input/src/clipboard.rs (declared in that crate's Cargo.toml).
- Errors beyond those first dependency failures are not known: cargo stops at the first crate that fails.
Limitations:
- Compile check only; nothing was run on a device. Artifact download is blocked from this session (proxy), so results were read from the run logs through gh.

## S-2026-11-18-13 — Patches as files, probe run 3
Date: 2026-11-18
Commit: pending
Goal: Continue the Android compile probe with minimal, separate changes to upstream.
Changes:
- Added patches/upstream/0001 (winit activity) and 0002 (arboard fallback, new android_clipboard.rs); workflow now applies them; D-012 recorded.
Verification:
- Both patches pass `git apply --check` against upstream 8989a51 locally. Validator PASSED locally. CI run: pending (result recorded in the next entry).
Limitations:
- Patched code not yet compiled.

## S-2026-11-18-14 — Android probe run 3 results
Date: 2026-11-18
Commit: pending
Goal: Read the result of the probe with patches 0001 and 0002 (run 44377119048, workflow at 23676ae, upstream 8989a51).
Changes:
- None to code; results recorded.
Verification:
- Both patches applied in CI. The earlier failures (android-activity, arboard) are gone: dependency crates now compile further.
- horizon-client-render still FAILED, now in upstream's own code, crate horizon-client-input, crates/horizon-client-input/src/keybinds.rs: it uses winit's desktop-only `platform::modifier_supplement` (line 306, `key_without_modifiers`) and `platform::scancode` (line 359, `to_scancode`), which winit builds only for Windows, macOS, X11 and Wayland. The same `modifier_supplement` import is in crates/horizon-client/src/game/runtime/window_handler.rs:440.
- horizon-client FAILED earlier, in the build script of `hidapi 2.6.7` (controller input): it looks for libudev through pkg-config and cannot cross-compile for aarch64-linux-android.
- Errors in code after these are not known (cargo stops at the first failing crate).
Limitations:
- Compile check only; nothing run on a device.

## S-2026-11-19-01 — Patches 0003 and 0004
Date: 2026-11-19
Commit: pending
Goal: Fix the next Android compile blockers with minimal, Android-gated patches.
Changes:
- Added patches/upstream/0003 (android_keys.rs stand-ins) and 0004 (dualsense gated off on Android like iOS).
Verification:
- All four patches pass `git apply --check` against 8989a51 and apply together. Diffs read: every change is under an Android cfg or in a file that is compiled only on Android. CI result: pending.
Limitations:
- Patched code not compiled yet; desktop build with patches not run.

## S-2026-11-19-02 — Probe run 4 and refreshed patches
Date: 2026-11-19
Commit: pending
Goal: Read run 44463236029 (workflow at b70c200) and keep going.
Changes:
- Run 4 result: patches 0002 and 0003 did not apply (upstream had moved from 8989a51 to b45d73d) but the job went on; its compile errors (only `winit::platform::scancode` in horizon-client-input, for both checks) therefore do not describe the patched tree. 0001 and 0004 did apply.
- Upstream commit 5cfd9f1 already contains the winit-activity, arboard and learn_layout fixes. Dropped those patches, rewrote the key patch (scancode, key_without_modifiers) and kept the dualsense one. Workflow now stops on a patch failure.
Verification:
- Both new patches pass `git apply --check` and apply together on b45d73d. CI: pending.
Limitations:
- Not compiled; desktop build with patches not run; no device test.

## S-2026-11-19-03 — Probe run 5 and patch 0003
Date: 2026-11-19
Commit: pending
Goal: Read run 44464171681 (workflow at cee959d, upstream b45d73d, both patches applied).
Changes:
- Run 5 result: both patches applied; hidapi and horizon-client-input no longer fail; the many dependency crates compile. horizon-client (lib) FAILED with 2 errors, the same cause: crates/horizon-client/src/entry.rs lines 27 and 42 call `horizon_mod_package::holder`, which exists only for Linux, macOS and Windows (with feature runtime-process).
- Added patches/upstream/0003-android-no-mod-holder.patch: skips the holder on Android.
Verification:
- All three patches pass `git apply --check` and apply together on b45d73d. CI: pending.
Limitations:
- Other users of `holder` exist outside the horizon-client check (crates/horizon-game/src/lib.rs:31, crates/horizon-hot-paths-game/src/main.rs:41) and may fail later. Compile check only; no device.

## S-2026-11-19-04 — Android cargo check passes
Date: 2026-11-19
Commit: pending
Goal: Read run 44464658215 (workflow at 7c6096b, upstream b45d73d + patches 0001-0003).
Changes:
- None to code; result recorded.
Verification:
- horizon-client (lib) `cargo check` for aarch64-linux-android FINISHED successfully (5m 00s). No errors.
Limitations:
- Type check only: not linked, not built in release, not run. Other crates that use `holder` (horizon-game, horizon-hot-paths-game) were not checked. Desktop build with patches not run.

## S-2026-11-19-05 — First APK pipeline
Date: 2026-11-19
Commit: pending
Goal: Build a first APK and check desktop is unaffected, as the user asked.
Changes:
- Added the Android shell, APK script, debug keystore, the two workflows, patch 0004 and extended 0003.
Verification:
- Patches 0001-0004 apply together on b45d73d (local). Workflow runs: pending.
Limitations:
- Nothing compiled for the shell yet; no device test.

## S-2026-11-19-06 — Desktop check caught an error in patch 0004
Date: 2026-11-19
Commit: pending
Goal: Read the first desktop check (run 44466410937, workflow at 4d2ae80).
Changes:
- Fixed patch 0004: `EventLoopError` is imported from `winit::error`, not `winit::event_loop` (private there).
Verification:
- Desktop `cargo check -p horizon-client` on Linux FAILED with E0603 (`EventLoopError` is private) in the new android_app.rs; this is the error of the patch, not of upstream. The Android APK build (run 44466413729) was compiling on the same wrong patch.
- Patches 0001-0004 still apply together on b45d73d (local).
Limitations:
- Desktop result after the fix: pending.

## S-2026-11-19-07 — First APK built and published
Date: 2026-11-19
Commit: pending
Goal: Read the desktop check re-run and the APK build.
Changes:
- None to code; results recorded.
Verification:
- Desktop check, run 44468287424 (upstream b45d73d + patches 0001-0004, `cargo check -p horizon-client`, Linux): SUCCESS.
- APK build, run 44468371147 (workflow android-apk.yml, same upstream): `cargo build --profile fast --target aarch64-linux-android -p rustdeck-android` finished in 21m 47s (1 warning: unused import `EventLoop` at the changed call sites). Library 176 MB, 126 MB after llvm-strip. APK 50,950,596 bytes, build-tools 37.0.0, signature verified by apksigner (v2 and v3 schemes true). Published as asset RustDeck-nightly.apk of the prerelease `nightly`: https://github.com/DreamingbullyYT/RustDeck/releases/download/nightly/RustDeck-nightly.apk (repository is public).
- Earlier runs with the wrong import in patch 0004 failed (desktop 44466410937, APK 44466413729).
Limitations:
- The APK has not been installed or started: whether it runs is unknown. Expected problems: I-009 (several event loops), game files/Java (I-006), graphics, mod host. A small unused-import warning remains in patch 0004.

## S-2026-11-19-08 — First start on the phone (user report)
Date: 2026-11-19
Commit: pending
Goal: Record what the user saw when starting RustDeck-nightly.apk (OnePlus 11, landscape).
Changes:
- None to code.
Verification:
- User screenshot at 09:20: a blank dark gray screen under the system status bar, nothing drawn. The app installed and opened a window; it did not close at that moment. Whether the engine is running or stuck is unknown. rustdeck.log not read yet.
Limitations:
- One screenshot only; no log, no logcat.


## S-2026-11-19-09 — First device log: root cause identified; Android stack bypass patch
Date: 2026-11-19
Commit: 33618a8 (patch file)
Goal: Diagnose the blank screen from the actual phone log and remove the fatal unsupported-platform startup exit with a minimal Android-only experiment.
Changes:
- User supplied `rustdeck.log`: only shell startup followed by `GuestStackUnavailable: GuestStackUnavailable: stack inspection is unsupported on this platform`.
- Inspected upstream `horizon-game::main()` and `horizon-guest-stack::prepare_main()`; `prepare_main()` calls `prepare()` and `process::exit(1)` on error. Android is excluded from the supported inspection cfgs, so startup exits before `entry::prepare()` or UI initialization.
- Added `patches/upstream/0005-android-guest-stack-startup.patch`, which makes Android `prepare()` return `Ok(())` and leaves other platform behavior unchanged. This is an experiment; the actual guest-stack safety on Android is unresolved.
- Observed upstream `main` at `2d02b5e1a04c0e2ed224d4f218dc57d64b838aac`.
Verification:
- Root cause confirmed by reading upstream source and matching it to the user's exact log.
- Patch file committed to RustDeck (`33618a8`). No patch application, build, validator, or device test has run yet.
Limitations:
- The Android-only bypass may expose another startup blocker or stack-safety problem; `check_current()` remains unsupported on Android. Build and test before claiming fix.


## S-2026-11-19-10 — Correct patch 0005 syntax after CI caught malformed hunk
Date: 2026-11-19
Commit: 2c07940 (hunk-count correction)
Goal: Run the Android build after the startup fix; correct patch formatting caught before compilation.
Changes:
- Android APK workflow run 44496319805 failed at `git apply`: patch 0005 was corrupt because its hunk line counts were wrong.
- Corrected hunk header from 6/14 to 8/17 and committed the correction; a new run should be triggered by the patch change.
Verification:
- Failure was precisely identified in the Actions log; hunk counts manually recalculated from context/additions/deletions.
- Corrected patch is committed; awaiting a fresh patch-apply/build run.
Limitations:
- No successful patch application, compilation or device test yet.
