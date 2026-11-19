# Roadmap

## Phase 0 — Foundation
- PMCS in place and green in CI.
- Upstream `main` revision observed and recorded in UPSTREAM_SYNC.md.
- UI stack and first milestone decided in DECISIONS.md.

## Phase 1 — Launcher core
- Minimal launcher core aligned with upstream launcher core, with the official-launcher-style mobile UI (D-006).
- Verified build in CI. (2026-11-19: Android `cargo check` is green; first APK built and published. First device log identified a fatal unsupported guest-stack inspection; patch 0005 is an untested experiment.)

## Phase 2 — Engine management
- Fetch, verify, install and automatically update Horizon engine builds from this repo.
- Same for launcher core and UI, without a new APK (D-007).

## Phase 3 — Upstream tracking
- Repeatable procedure to follow newer upstream `main` engine and launcher-core revisions with recorded compatibility results.

## Exit criteria
- Phase 0 is done when PMCS CI is green, the upstream revision is recorded and the stack decision is recorded.
- Later phases get their own exit criteria when they are planned.
