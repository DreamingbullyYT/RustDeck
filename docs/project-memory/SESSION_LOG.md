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
