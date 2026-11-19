# Current State

Last updated: 2026-11-19
Status: ANDROID_ENGINE_SHELL_RUNTIME_VERIFIED; LAUNCHER_UI_NOT_IMPLEMENTED
Repository HEAD: resolve dynamically from current main

## Direction
- RustDeck is a from-scratch launcher for Horizon, aiming to stay on the newest engine and launcher core, as close to upstream Horizon (PC) `main` as possible, with no version pinning.
- UI and behavior mirror the official Minecraft launcher, mobile friendly.
- Auto-updates from this repo; new APK only for new permissions; engine, launcher core and UI updatable without APK (D-007).

## Implemented
- PMCS continuity docs, validator (scripts/validate_project_memory.py), regression tests and GitHub Actions workflow, adapted from Rusty-survival-.

- .github/workflows/android-client-probe.yml: manual experiment that runs `cargo check` of upstream's horizon-client-render and horizon-client for aarch64-linux-android. Results (2026-11-18): it fails to compile; first blockers are the winit activity feature and arboard (see SESSION_LOG S-2026-11-18-12).

## Not implemented / not verified
- No RustDeck launcher UI code yet. First APK is only the Android NativeActivity test shell (D-013), built and published as `nightly`; user installed and launched it. The screenshot shows Horizon's upstream `No prepared game files` fallback UI, not the RustDeck launcher UI. This verifies shell + Horizon UI startup after patch 0005, but not game preparation or playable Minecraft.
- .github/workflows/desktop-check.yml: manual desktop `cargo check` with the patches (passed on 2026-11-19 after a fix to patch 0004: run 44468287424).
- PMCS CI: the last 5 runs on main passed (checked 2026-11-18, Actions).
- First Android runtime observation uses Horizon upstream revision `2d02b5e1a04c0e2ed224d4f218dc57d64b838aac` (observed for run 44496466298; see UPSTREAM_SYNC.md).
- UI is Kotlin with Compose (D-010); update mechanism/format, packaging and the Kotlin-to-Rust bridge are UNDECIDED. Release and nightly channels; CI builds automatically only when upstream has a new release or nightly, and skips otherwise; the newest build is installed (D-008).
- Update-without-APK on Android is not proven (see I-003).

## Next actions
0. DONE — First Android runtime screen (2026-11-19): after patch 0005 bypassed the unsupported guest-stack inspection abort, user launched the APK and reached Horizon's `No prepared game files` screen. This is upstream `crates/horizon-client/src/no_resources.rs` / `game/runtime/no_resources.rs`. It reports no prepared resources at `.local/horizon-client/26.3`. Android shell startup and Horizon rendering are now VERIFIED to this point; no game resources have been prepared and gameplay is not yet verified. Next: build RustDeck's actual launcher flow so it prepares game files before starting the engine.
1. Confirm PMCS CI is green on main.
1a. DONE: Android `cargo check` of horizon-client passes with patches 0001-0003 (run 44464658215); desktop `cargo check` passed with patches (run 44468287424); Android APK built and device-launched to Horizon's no-resources screen (run 44496466298 plus user screenshot).
1b. Resolve I-004 (Horizon client on Android, LGPL) and I-006 (game preparation without Java).
2. Look at upstream Horizon `main` and record the observed revision in UPSTREAM_SYNC.md.
3. Decide the Kotlin-to-Rust bridge and first milestone and record them in DECISIONS.md.
4. Prove on a real device that engine/launcher/UI payloads can be updated without a new APK (I-003).
