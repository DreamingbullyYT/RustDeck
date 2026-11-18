# Current State

Last updated: 2026-11-18
Status: PMCS_BOOTSTRAPPED; NO_LAUNCHER_CODE_YET
Repository HEAD: resolve dynamically from current main

## Direction
- RustDeck is a from-scratch launcher for Horizon, aiming to stay on the newest engine and launcher core, as close to upstream Horizon (PC) as possible.

## Implemented
- PMCS continuity docs, validator (scripts/validate_project_memory.py), regression tests and GitHub Actions workflow, adapted from Rusty-survival-.

## Not implemented / not verified
- No launcher code.
- PMCS CI result on this repository is pending.
- No pinned upstream revision recorded yet (see UPSTREAM_SYNC.md).
- UI stack, platform targets and packaging are UNDECIDED.

## Next actions
1. Confirm PMCS CI is green on main.
2. Record the pinned upstream Horizon revision in UPSTREAM_SYNC.md.
3. Decide scope and stack for the first launcher milestone and record it in DECISIONS.md.
