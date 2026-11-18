# Project Memory

## Identity
Project: RustDeck
Repository: DreamingbullyyT/RustDeck
Upstream: Horizon (PC)
Related repo: DreamingbullyyT/Rusty-survival- (DreamMod; its launcher work is out of scope there and lives here)

## Mission
Build a launcher for Horizon from scratch, with the engine version and launcher core as recent as possible and as close to upstream Horizon (PC) as possible.

## Ground rules
- Start from zero; do not port code from earlier launcher attempts unless a decision in DECISIONS.md says so.
- Track upstream: pin exact upstream revisions in UPSTREAM_SYNC.md and update them deliberately.
- Keep divergence from upstream minimal, explicit and recorded.
- Work directly on main; no PRs or feature branches unless the user changes this rule.
- No fabricated test results or completion claims.
- Keep session history append-only.
- Undecided choices are marked UNDECIDED; do not assume them.

## Architecture
See ARCHITECTURE.md.

## Continuity system (PMCS)
PMCS documents live in docs/project-memory/. They exist so work survives context compression and separate AI sessions. Read AI_HANDOFF.md first. Update project memory with every meaningful implementation or architecture change. CI runs scripts/validate_project_memory.py to enforce structure, append-only history and memory updates alongside code changes.
