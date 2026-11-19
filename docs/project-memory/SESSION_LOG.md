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


## S-2026-11-19-11 — Corrected patch applies on current upstream; APK build underway
Date: 2026-11-19
Commit: 2c07940 (tested in workflow run 44496466298)
Goal: Confirm patch 0005 is syntactically valid and applies to latest Horizon main.
Changes:
- None after the hunk-count correction; observed workflow run 5.
Verification:
- Android workflow step “Apply RustDeck patches and add the Android shell” succeeded with all patches 0001-0005 against current upstream main (observed upstream 2d02b5e1).
- Android cargo build is still running; APK packaging/device testing not yet complete.
Limitations:
- Successful patch application is not a compilation or runtime result.


## S-2026-11-19-12 — First successful Android runtime screen; identify upstream no-resources UI
Date: 2026-11-19
Commit: pending
Goal: Interpret the user's screenshot after installing the APK built with Android guest-stack patch 0005 and align PMCS state with observed runtime behavior.
Changes:
- User screenshot shows Horizon's “No prepared game files” screen, with searched path `.local/horizon-client/26.3`.
- Confirmed exact upstream source at Horizon revision `2d02b5e1a04c0e2ed224d4f218dc57d64b838aac`: `crates/horizon-client/src/no_resources.rs` draws this screen; `crates/horizon-client/src/game/runtime/no_resources.rs` selects and runs it when prepared game files are absent.
- Upstream source comments explicitly describe it as a game started by hand instead of through a launcher. It is not RustDeck's intended launcher UI.
- Updated CURRENT_STATE.md to record Android shell + Horizon rendering verified to this point, and clarified ARCHITECTURE.md status: Compose/UI direction accepted, implementation details still open.
Verification:
- User-provided device screenshot confirms the Android app progressed past the prior startup failure and rendered Horizon's no-resources UI.
- Source inspected directly from the exact upstream revision used by the Android build.
Limitations:
- No prepared Minecraft files, actual RustDeck launcher UI, game launch or gameplay verified. This is still the test shell described by D-013.
Next:
- Implement the Kotlin/Jetpack Compose launcher UI and connect it to Rust launcher core so game files are prepared before starting Horizon, following D-006 through D-011 and keeping upstream divergence minimal.


## S-2026-11-19-13 — Add first Kotlin/Compose launcher app and Gradle APK packaging
Date: 2026-11-19
Commit: bf11cdc (workflow trigger update; app files committed immediately before)
Goal: Replace the test-shell-only Android entry point with the first real RustDeck launcher UI while preserving Horizon's native engine as a separate Activity.
Changes:
- Added root Gradle project files: `settings.gradle.kts`, `build.gradle.kts`, `gradle.properties`.
- Added `app/` Android application module with Kotlin 2.2.21, AGP 8.13.2, Compose compiler plugin and Compose BOM 2025.10.00.
- Added `MainActivity.kt`: Home, Installations and Settings screens; animated gradient background; responsive scroll region; guest/account indicator; Nightly channel information. The install CTA navigates to Installations. It deliberately does not claim to install or launch a game.
- Replaced the old NativeActivity launcher manifest with an app manifest: Compose `MainActivity` is the launcher entry point; Horizon `android.app.NativeActivity` remains a separate non-exported landscape Activity.
- Migrated APK packaging workflow to Gradle: it builds the Rust native library, copies it into `app/src/main/jniLibs/arm64-v8a`, assembles the Compose APK, and publishes it to the existing nightly prerelease. Added `app/**` to workflow path triggers.
- Removed obsolete top-level NativeActivity-only manifest and direct aapt2 packaging script.
- Added D-014 describing the Compose/engine Activity split; updated ARCHITECTURE, CURRENT_STATE, ROADMAP, AI_HANDOFF and I-002.
Verification:
- GitHub workflow run 44501297217 started but was cancelled by later commits while Rust native compilation was in progress. No Gradle configuration or Kotlin compilation result exists yet.
- No device installation or visual verification of the Compose UI yet.
Limitations / risks:
- The Kotlin-to-Rust launcher-core bridge remains undecided. The UI currently uses presentation-only state and has no install/auth/version/prepare functionality.
- D-007 (update UI/core/engine without APK) remains unproven; dynamically updating Kotlin/Compose code is a separate Android packaging/security problem.
- Android guest stack safety, game preparation/JDK 25 and multiple winit event-loop behavior remain open.
Next:
- Let the new Android workflow complete; fix Gradle/Kotlin/manifest errors from real logs.
- Install the Compose APK and verify both portrait and landscape.
- Then design the Rust launcher-core bridge and preparation flow before enabling Play.


## S-2026-11-19-14 — Inspect upstream launcher core for Android bridge
Date: 2026-11-19
Commit: pending
Goal: Identify the lowest-divergence way to connect the Compose UI to Horizon's launcher logic.
Findings:
- Horizon has a reusable shared launcher library: `launcher/src/lib.rs` describes itself as “The launcher's core, shared by the GUI, the CLI and the MCP server.” It exports `backend`, `account`, `data_location`, `java`, `launch`, `sources`, etc.
- Public backend operations include release listing, installation, installed-version listing, preparation selection and in-process preparation. Reusing this is preferable to copying upstream logic.
- The launcher package currently includes desktop GPUI dependencies; Android compilation of the library without desktop UI dependencies has not been tested.
- Upstream `backend::PLATFORMS` currently has only Linux x86_64, macOS x86_64/ARM64 and Windows x86_64/ARM64. Android has no release asset entry. RustDeck's Android engine is a NativeActivity shared library, so the desktop launcher's process-spawn path cannot simply be reused for Play.
- The likely next technical experiment is a dependency-only Android cargo check of the upstream launcher library with desktop-only dependencies gated/disabled, followed by a small JNI boundary. Do not implement a second copy of the entire backend before this experiment.
Verification:
- Read upstream `launcher/src/lib.rs`, `launcher/src/backend.rs`, `launcher/src/backend/release_assets.rs`, `launcher/Cargo.toml`, `crates/horizon-platform/src/lib.rs`, and `crates/horizon-prepare/src/lib.rs` at revision `2d02b5e1a04c0e2ed224d4f218dc57d64b838aac`.
- This is source inspection only; no Android compile of `horizon-launcher` library has been run.
Limitations:
- Android engine artifact discovery/signature and loading, Java 25 availability, and game preparation on Android remain unresolved (I-006, I-011, I-003).


## S-2026-11-19-15 — Diagnose first Compose APK workflow failure and fix SDK setup
Date: 2026-11-19
Commit: 398dcb9 (workflow fix)
Goal: Diagnose the failed first Compose APK build and repair the failing step without changing application code speculatively.
Evidence:
- GitHub Actions run 44501544949 failed: https://github.com/DreamingbullyYT/RustDeck/actions/runs/44501544949
- The Android Rust native library build step completed successfully.
- Failure occurred at `Set up Android SDK` using `android-actions/setup-android@v3`, before SDK platform install, Gradle setup, Kotlin compilation or APK packaging.
- Job log shows the action selected preinstalled sdkmanager 16.0, printed “Wrong version in preinstalled sdkmanager”, then attempted `sdkmanager tools`; SDK manager returned “Failed to find package 'tools'” and exit code 1.
Change:
- Removed the obsolete `android-actions/setup-android@v3` step. Ubuntu runner already provides `ANDROID_HOME` and command-line tools; workflow now calls `$ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager` directly, accepts licenses and installs `platforms;android-36` and `build-tools;36.0.0`.
- No Kotlin, Gradle or engine source was changed in this fix.
Verification:
- Failure cause is confirmed from the job logs.
- Workflow fix committed as `398dcb9ebccdec8ef869db80745d61dfe00d8228`; retry/build result is pending.
Next:
- Inspect the next Android APK workflow run. If SDK install succeeds, proceed to Gradle/Kotlin compilation; do not call the app build verified until the APK is produced.


## S-2026-11-19-16 — Fix sdkmanager license pipe false failure
Date: 2026-11-19
Commit: 72c6880dc45cffd15da1a429862f164f0166b1c3
Goal: Fix second Android APK workflow failure.
Evidence:
- Run 44506285451: Rust native library compilation passed, Java setup passed, then SDK platform install step failed before Gradle.
- Log: `yes: standard output: Broken pipe`; command was `yes | sdkmanager --licenses`. This is caused by sdkmanager closing stdin after it has accepted/processed the license prompts while shell `pipefail` treats the `yes` SIGPIPE as pipeline failure.
Change:
- SDK install step now uses `set -eu` (no `pipefail`) only for the license pipe. The final sdkmanager package-install command remains checked normally and fails the step if it fails.
- No source code or SDK package selection changed.
Verification:
- Failure diagnosed from job log. Fix committed; retry/build pending.
Next:
- Watch next APK workflow. If SDK packages install, debug Gradle or Kotlin build from logs as needed.


## S-2026-11-19-17 — Diagnose Android Rust compile failure and patch cfg mismatch
Date: 2026-11-19
Commit: 16d6f4f7ec30a89b2873e3b0e9061db97867a414 (patch 0006)
Goal: Diagnose third Compose APK workflow failure and fix the Android-only compile issue with a minimal patch.
Evidence:
- Run 44513128759 failed in `cargo build rustdeck-android`; SDK, Gradle and Kotlin steps were skipped.
- Compiler error E0433 at `crates/horizon-mod-package/src/runtime_leases.rs:431`: `crate::holder` absent on Android. Upstream `crates/horizon-mod-package/src/lib.rs` cfg-gates `holder` and `local_control` to Linux/macOS/Windows, while `runtime_leases::lease_units()` was still compiled with `runtime-process` on Android and calls `holder::lease_paths`. E0277 for unsized `[u8]` is derivative from this unresolved call.
Change:
- Added `patches/upstream/0006-android-gate-holder-dependent-lease-units.patch`, adding the same desktop OS cfg to `lease_units`; helper is not used by the Android shell. No engine behavior changes on desktop.
Verification:
- Cause confirmed from the Actions compiler log and upstream source.
- Patch created; next build must confirm it applies and compiles. Not yet verified.
Next:
- Inspect next Android workflow run. If native compile passes, continue SDK/Gradle/Kotlin packaging and address the next actual error.


### Follow-up — patch 0006 hunk syntax correction (2026-11-19)
The first retry run 44514421085 failed before compilation: `git apply` reported patch 0006 was corrupt at line 12. The hunk header declared 9 added lines but the hunk contained only 7. Corrected the header from `@@ -394,6 +394,9 @@` to `@@ -394,6 +394,7 @@` in commit 6497387fcc17315818ee7fb244e0320f9ba9afeb. The next run must verify both patch application and the original Rust compile fix; no compile result is claimed yet.


### Follow-up 2 — narrow patch 0006 context to upstream function line (2026-11-19)
Run 44514542263 failed during patch application: `git apply` could not match the overly broad comment hunk in upstream revision `908e3429b8c8487272a9bb3f3b18666c4262c6df`. Replaced the hunk with a minimal one-line context at the exact `pub fn lease_units(` line (425), commit `c604c11e27d00b3d0480e9176714889ff9fe09c8`. New Android run 44514679341 is in progress; patch application and compilation remain unverified.

## S-2026-11-19-18 — Diagnose Kotlin JVM target failure
Date: 2026-11-19
Commit: pending
Goal: Fix the failing Android APK workflow (run 44514679341, workflow at c604c11).
Evidence:
- Patch 0006 now applies and the Rust native library compiles (the earlier failures were patch hunks written by hand; see the two follow-ups above).
- The run then failed in Gradle `:app:compileDebugKotlin`: "Inconsistent JVM-target compatibility detected for tasks 'compileDebugJavaWithJavac' (1.8) and 'compileDebugKotlin' (17)". Java defaulted to 1.8 while Kotlin used the JDK 17 set up by the workflow.
Change:
- app/build.gradle.kts: `compileOptions` source and target compatibility set to Java 17. Nothing else changed.
Verification:
- Cause read from the job log. The fix is not yet verified: the next APK run must pass `:app:compileDebugKotlin`.
Next:
- Read the next run; address the next real error, if any. Not VERIFIED until an APK is produced and installed.

## S-2026-11-19-19 — Compose APK build succeeded
Date: 2026-11-19
Commit: 8845edf
Goal: Confirm the Java 17 fix.
Evidence: Android APK run 44520511269 completed with success; the nightly prerelease holds RustDeck-nightly.apk (about 67.6 MB). Issue ID of the patch-hunk lesson renamed I-010 to I-013 (duplicate ID).
Verification: APK produced by CI; not yet installed on a device, so not VERIFIED.
Next: user installs the nightly APK; check the Compose UI renders.
