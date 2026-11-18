# Changelog

## 2026-11-18 — Bootstrap PMCS
- Change ID: C-2026-11-18-01
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: Need durable project memory that survives context compression, as in Rusty-survival-.
- Root cause: New repository with no continuity system.
- Files/modules affected: docs/project-memory/*, scripts/validate_project_memory.py, tests/test_validate_project_memory.py, .github/workflows/project-memory.yml
- Implementation: Copied the PMCS from Rusty-survival- and adapted it for RustDeck. Replaced PARITY_MATRIX and REUSE_MATRIX with UPSTREAM_SYNC. Updated the validator's required files and markers.
- Tests/commands: see Exact results
- Exact results: validator PASSED; 4 regression tests OK (local). See SESSION_LOG.md entry S-2026-11-18-01
- Not tested: GitHub Actions run on this repository
- Risks / follow-up: Verify CI is green (I-001).

## 2026-11-18 — Upstream-main tracking and official-launcher UI decisions
- Change ID: C-2026-11-18-02
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: User decided to follow upstream `main` without pinning and to mirror the official Minecraft launcher, mobile friendly.
- Root cause: Earlier docs assumed pinned upstream revisions.
- Files/modules affected: docs/project-memory/* (AI_HANDOFF, PROJECT_MEMORY, CURRENT_STATE, ARCHITECTURE, DECISIONS, ISSUES, ROADMAP, COMPATIBILITY, UPSTREAM_SYNC), scripts/validate_project_memory.py
- Implementation: Added D-005 and D-006; replaced pinning wording with observed-revision wording; validator marker "Pinned upstream" became "Tracked upstream".
- Tests/commands: python scripts/validate_project_memory.py; python -m unittest discover -s tests
- Exact results: validator PASSED; 4 regression tests OK (local). See SESSION_LOG.md entry S-2026-11-18-02
- Not tested: GitHub Actions run
- Risks / follow-up: Without pinning, builds are not reproducible; record the observed upstream revision per build.

## 2026-11-18 — Auto-update decision
- Change ID: C-2026-11-18-03
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: User wants newest engine and launcher always, updated automatically from our repo, with an APK only for new permissions and an updatable UI.
- Root cause: Update model was not yet recorded.
- Files/modules affected: docs/project-memory/* (DECISIONS, ARCHITECTURE, CURRENT_STATE, ISSUES, ROADMAP, AI_HANDOFF)
- Implementation: Added D-007 and I-003; recorded the implied APK-shell plus updatable-payload structure as not yet designed.
- Tests/commands: python scripts/validate_project_memory.py; python -m unittest discover -s tests
- Exact results: see SESSION_LOG.md entry S-2026-11-18-03
- Not tested: GitHub Actions run; any update mechanism on a device
- Risks / follow-up: Prove update-without-APK on Android (I-003).

## 2026-11-18 — No update channels
- Change ID: C-2026-11-18-04
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: User does not care about the update channel; the newest build should be installed.
- Root cause: Channel policy was left open.
- Files/modules affected: docs/project-memory/* (DECISIONS, ARCHITECTURE, CURRENT_STATE)
- Implementation: Added D-008; the mechanism stays an implementation detail.
- Tests/commands: python scripts/validate_project_memory.py; python -m unittest discover -s tests
- Exact results: validator PASSED; tests OK (local)
- Not tested: GitHub Actions run
- Risks / follow-up: Newest-wins with no channels means a bad build reaches everyone; consider this when designing the updater.
