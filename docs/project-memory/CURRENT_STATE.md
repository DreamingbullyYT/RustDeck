# Current State

Last updated: 2026-11-19
Status: COMPOSE_LAUNCHER_IMPLEMENTED; ANDROID_BUILD_PENDING
Repository HEAD: resolve dynamically from current main

## Direction
- RustDeck is a from-scratch launcher for Horizon, aiming to stay on the newest engine and launcher core, as close to upstream Horizon (PC) `main` as possible, with no version pinning.
- UI and behavior mirror the official Minecraft launcher, mobile friendly.
- Auto-updates from this repo; new APK only for new permissions; engine, launcher core and UI updatable without APK (D-007).

## Implemented
- PMCS continuity docs, validator (scripts/validate_project_memory.py), regression tests and GitHub Actions workflow, adapted from Rusty-survival-.

- .github/workflows/android-client-probe.yml: manual experiment that runs `cargo check` of upstream's horizon-client-render and horizon-client for aarch64-linux-android. Results (2026-11-18): it fails to compile; first blockers are the winit activity feature and arboard (see SESSION_LOG S-2026-11-18-12).

## Not implemented / not verified
- Added first Kotlin/Jetpack Compose launcher app: Home, Installations and Settings screens, animated dark background, responsive scrolling, guest/account state and Nightly channel display. This is UI foundation only; launcher core, authentication, installation/download, resource preparation and Play integration are not implemented. Android engine NativeActivity remains a separate non-launcher activity (D-014). The first Compose APK build is pending.
- .github/workflows/desktop-check.yml: manual desktop `cargo check` with the patches (passed on 2026-11-19 after a fix to patch 0004: run 44468287424).
- PMCS CI: the last 5 runs on main passed (checked 2026-11-18, Actions).
- First Android runtime observation uses Horizon upstream revision `2d02b5e1a04c0e2ed224d4f218dc57d64b838aac` (observed for run 44496466298; see UPSTREAM_SYNC.md).
- UI is Kotlin with Compose (D-010). The app now has a Gradle Android application module; Kotlin-to-Rust bridge and update mechanism/format remain UNDECIDED. APK workflow is being moved from direct aapt2 packaging to Gradle. Release and nightly channels; CI builds automatically only when upstream has a new release or nightly, and skips otherwise; the newest build is installed (D-008).
- Update-without-APK on Android is not proven (see I-003).

## Next actions
0. DONE — Horizon NativeActivity reached its no-resources fallback UI on device after patch 0005. This proves the native shell can render, not game preparation. The next APK replaces the NativeActivity launcher entry with the Compose RustDeck Activity and keeps Horizon NativeActivity separate.
0a. IMPLEMENTED — first Compose UI foundation (2026-11-19): Home, Installations and Settings; animated background; separate engine NativeActivity; Gradle Android project and updated APK workflow. Build/device verification pending.
1. Confirm PMCS CI is green on main.
1a. DONE: Android `cargo check` of horizon-client passes with patches 0001-0003 (run 44464658215); desktop `cargo check` passed with patches (run 44468287424); Android APK built and device-launched to Horizon's no-resources screen (run 44496466298 plus user screenshot).
1b. Resolve I-004 (Horizon client on Android, LGPL) and I-006 (game preparation without Java).
2. Look at upstream Horizon `main` and record the observed revision in UPSTREAM_SYNC.md.
3. Decide the Kotlin-to-Rust bridge and first milestone and record them in DECISIONS.md.
4. Prove on a real device that engine/launcher/UI payloads can be updated without a new APK (I-003).
