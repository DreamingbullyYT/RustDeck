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
The launcher always uses the newest engine and launcher builds and updates them automatically from this repository (DreamingbullyyT/RustDeck). A new APK is required only when new Android permissions are needed. Engine, launcher core and the UI must all be updatable without a new APK. Channel policy is in D-008.

## D-008 — Release and nightly channels; newest build is installed
Date: 2026-11-18 (corrected several times the same day; see CHANGELOG C-2026-11-18-05, -06 and -08)
Status: ACCEPTED
Two channels: release and nightly.
- Release: RustDeck's own release (launcher), made only when the build is stable enough. Upstream's game has no releases (only Nightly, see UPSTREAM_SYNC.md), so release is about our launcher.
- Nightly: built automatically so we always have the newest engine (and other components). CI builds the APK, libraries and other outputs only when upstream has a new nightly or release; if upstream has nothing new, CI does not build.
The newest available build is the one that gets installed. The exact mechanism (how CI detects new upstream builds, GitHub Releases vs CI artifacts, schedule, verification) and whether the player can choose a channel are UNDECIDED and recorded when the updater and CI are built.

## D-009 — Official-launcher-style UI on top of the upstream-aligned launcher core
Date: 2026-11-18
Status: ACCEPTED
The UI looks like the official Mojang launcher, polished, with an animated background and similar visual touches, and is mobile friendly (D-006). It is bound to the launcher core (Rust logic aligned with the upstream launcher: version listing, install, signature checks, game preparation). Upstream's GPUI desktop UI is not reused. Still open: UI technology (I-002) and LGPL obligations if upstream launcher code is reused (I-004).

## D-010 — Kotlin and Jetpack Compose for the UI
Date: 2026-11-18
Status: ACCEPTED
The Android UI is written in Kotlin with Jetpack Compose (animated background and official-launcher look, D-009). The launcher core stays in Rust, aligned with the upstream launcher. How Kotlin talks to the Rust core (JNI, CLI process, other) is UNDECIDED.
