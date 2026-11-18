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
