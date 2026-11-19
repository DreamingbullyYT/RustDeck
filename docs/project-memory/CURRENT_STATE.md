# Current State

Last updated: 2026-11-18
Status: PMCS_BOOTSTRAPPED; NO_LAUNCHER_CODE_YET
Repository HEAD: resolve dynamically from current main

## Direction
- RustDeck is a from-scratch launcher for Horizon, aiming to stay on the newest engine and launcher core, as close to upstream Horizon (PC) `main` as possible, with no version pinning.
- UI and behavior mirror the official Minecraft launcher, mobile friendly.
- Auto-updates from this repo; new APK only for new permissions; engine, launcher core and UI updatable without APK (D-007).

## Implemented
- PMCS continuity docs, validator (scripts/validate_project_memory.py), regression tests and GitHub Actions workflow, adapted from Rusty-survival-.

- .github/workflows/android-client-probe.yml: manual experiment that runs `cargo check` of upstream's horizon-client-render and horizon-client for aarch64-linux-android. Results (2026-11-18): it fails to compile; first blockers are the winit activity feature and arboard (see SESSION_LOG S-2026-11-18-12).

## Not implemented / not verified
- No launcher UI code. First APK: android/shell + android/build_apk.sh + .github/workflows/android-apk.yml (D-013); build result in SESSION_LOG.
- .github/workflows/desktop-check.yml: manual desktop `cargo check` with the patches.
- PMCS CI: the last 5 runs on main passed (checked 2026-11-18, Actions).
- No upstream revision observed/recorded yet (see UPSTREAM_SYNC.md).
- UI is Kotlin with Compose (D-010); update mechanism/format, packaging and the Kotlin-to-Rust bridge are UNDECIDED. Release and nightly channels; CI builds automatically only when upstream has a new release or nightly, and skips otherwise; the newest build is installed (D-008).
- Update-without-APK on Android is not proven (see I-003).

## Next actions
1. Confirm PMCS CI is green on main.
1a. DONE: Android `cargo check` of horizon-client passes with patches 0001-0003 (run 44464658215). Next: a desktop `cargo check` with the patches to prove desktop is unchanged, then a real Android build (link to a shared library, find the Android entry point) and a test on the phone; decide which fixes go upstream as PRs. Then test on the phone (I-004) and try bundled Java (D-011, I-006).
1b. Resolve I-004 (Horizon client on Android, LGPL) and I-006 (game preparation without Java).
2. Look at upstream Horizon `main` and record the observed revision in UPSTREAM_SYNC.md.
3. Decide the Kotlin-to-Rust bridge and first milestone and record them in DECISIONS.md.
4. Prove on a real device that engine/launcher/UI payloads can be updated without a new APK (I-003).
