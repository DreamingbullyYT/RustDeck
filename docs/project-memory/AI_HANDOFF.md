# AI Handoff — Mandatory Entry Point

Last updated: 2026-11-18
Project: RustDeck
Repository: DreamingbullyyT/RustDeck
Product: launcher for Horizon (Minecraft Java rewritten in Rust)
Upstream: Horizon for PC. Goal: engine version and launcher core as new and as close to upstream as possible.

## Mandatory onboarding
1. Read this file, then PROJECT_MEMORY.md, CURRENT_STATE.md, ARCHITECTURE.md, DECISIONS.md, ISSUES.md, ROADMAP.md, COMPATIBILITY.md, UPSTREAM_SYNC.md, CHANGELOG.md, SESSION_LOG.md and CHANGE_TEMPLATE.md.
2. Inspect current branch, HEAD commit, working tree and recent history. Repository state is authoritative over stale prose.
3. Work directly on `main`; no feature branches or pull requests unless the user changes this rule.
4. Read the latest SESSION_LOG.md entry and open issues in ISSUES.md before proposing work.
5. Treat source, Git history, reproducible tests, pinned upstream references and build logs as ground truth. Never invent progress.
6. Before implementation, state intended change, affected modules, risks and verification plan.
7. After every meaningful change, update project memory in the same logical change set. Append to SESSION_LOG.md; never rewrite old entries.
8. Run the PMCS validator (`python scripts/validate_project_memory.py`) and the relevant tests.
9. Distinguish IMPLEMENTED from VERIFIED. IMPLEMENTED is not VERIFIED.

## Non-negotiable direction
- The launcher stays close to upstream Horizon (PC): track upstream engine and launcher-core revisions, record the pinned revision in UPSTREAM_SYNC.md, keep local divergence minimal and documented.
- Prefer upstream extension points over forks or copied code. Every intentional divergence gets an entry in DECISIONS.md.
- Anything not yet decided (UI stack, target platforms beyond Android, packaging) stays UNDECIDED until recorded in DECISIONS.md.

## Change record requirements
Every substantial change records date, commit, files, motivation, implementation, exact tests/results, risks and follow-up. Use CHANGE_TEMPLATE.md.

## Status vocabulary
OPEN, INVESTIGATING, IMPLEMENTED, VERIFIED, BLOCKED.

## Handoff rule
At the end of a work session, update CURRENT_STATE.md, ISSUES.md, ROADMAP.md, CHANGELOG.md and this handoff when relevant. Append a dated SESSION_LOG.md entry with exact work and verification. State clearly what was and was not tested.
