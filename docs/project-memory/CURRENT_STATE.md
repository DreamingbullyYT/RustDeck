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

## Not implemented / not verified
- No launcher code.
- PMCS CI result on this repository is pending.
- No upstream revision observed/recorded yet (see UPSTREAM_SYNC.md).
- UI stack, update mechanism/format and packaging are UNDECIDED. Release and nightly channels; CI builds automatically only when upstream has a new release or nightly, and skips otherwise; the newest build is installed (D-008).
- Update-without-APK on Android is not proven (see I-003).

## Next actions
1. Confirm PMCS CI is green on main.
2. Look at upstream Horizon `main` and record the observed revision in UPSTREAM_SYNC.md.
3. Decide the UI stack and first milestone and record them in DECISIONS.md.
4. Prove on a real device that engine/launcher/UI payloads can be updated without a new APK (I-003).
