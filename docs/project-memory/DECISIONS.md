# Decisions

## D-001 — Fresh start
Date: 2026-11-18
Status: ACCEPTED
RustDeck is built from zero. Earlier launcher attempts are not a code base.

## D-002 — Stay close to upstream
Date: 2026-11-18
Status: ACCEPTED
Engine version and launcher core follow upstream Horizon (PC) as closely and as recently as possible. Pin revisions in UPSTREAM_SYNC.md. Every intentional divergence gets its own decision entry.

## D-003 — Project memory system (PMCS)
Date: 2026-11-18
Status: ACCEPTED
Use the PMCS from Rusty-survival- to survive context compression: docs/project-memory/, append-only SESSION_LOG.md, CI validator.

## D-004 — Work on main
Date: 2026-11-18
Status: ACCEPTED
Commit directly to main; no feature branches or PRs unless the user changes this rule.
