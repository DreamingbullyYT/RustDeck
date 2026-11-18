# Current State

Last updated: 2026-11-18
Status: PMCS_BOOTSTRAPPED; NO_LAUNCHER_CODE_YET
Repository HEAD: resolve dynamically from current main

## Direction
- RustDeck is a from-scratch launcher for Horizon, aiming to stay on the newest engine and launcher core, as close to upstream Horizon (PC) `main` as possible, with no version pinning.
- UI and behavior mirror the official Minecraft launcher, mobile friendly.

## Implemented
- PMCS continuity docs, validator (scripts/validate_project_memory.py), regression tests and GitHub Actions workflow, adapted from Rusty-survival-.

## Not implemented / not verified
- No launcher code.
- PMCS CI result on this repository is pending.
- No upstream revision observed/recorded yet (see UPSTREAM_SYNC.md).
- UI stack, platform targets beyond mobile, and packaging are UNDECIDED.

## Next actions
1. Confirm PMCS CI is green on main.
2. Look at upstream Horizon `main` and record the observed revision in UPSTREAM_SYNC.md.
3. Decide the UI stack and first milestone and record it in DECISIONS.md.
