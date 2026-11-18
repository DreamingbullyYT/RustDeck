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

## D-005 — Track upstream main, no version pinning
Date: 2026-11-18
Status: ACCEPTED
RustDeck follows upstream Horizon `main` and does not pin versions. It always targets the newest engine and launcher core. The upstream revision actually used by a given build is recorded as an observation (UPSTREAM_SYNC.md, COMPATIBILITY.md), not as a pin.

## D-006 — Official-launcher look and behavior, mobile friendly
Date: 2026-11-18
Status: ACCEPTED
The UI and flows mirror the official Minecraft launcher, adapted to be mobile friendly. Mirror layout and behavior only; do not copy Mojang/Microsoft logos, trademarks or assets.

## D-007 — Automatic updates from our repo; APK only for new permissions
Date: 2026-11-18
Status: ACCEPTED
The launcher always uses the newest engine and launcher builds and updates them automatically from this repository (DreamingbullyyT/RustDeck). A new APK is required only when new Android permissions are needed. Engine, launcher core and the UI must all be updatable without a new APK. Update channel details (GitHub Releases vs CI artifacts, check frequency, verification) are UNDECIDED.
