# Roadmap

## Phase 0 — Foundation
- PMCS in place and green in CI.
- Upstream `main` revision observed and recorded in UPSTREAM_SYNC.md.
- UI stack and first milestone decided in DECISIONS.md.

## Phase 1 — Launcher UI and core
- [x] Decide Kotlin + Jetpack Compose UI (D-010).
- [x] Add initial Compose Home / Installations / Settings UI and separate engine NativeActivity (D-014); implementation is not yet build/device verified.
- [ ] Build and install the Compose APK; verify portrait and landscape rendering.
- [ ] Decide and implement Kotlin-to-Rust launcher-core bridge (I-011).
- [ ] Connect UI to upstream-aligned version listing, install, signature verification and preparation logic.
- [ ] Keep Play unavailable until game preparation is complete and verified.

## Phase 2 — Engine management
- Fetch, verify, install and automatically update Horizon engine builds from this repo.
- Same for launcher core and UI, without a new APK (D-007).

## Phase 3 — Upstream tracking
- Repeatable procedure to follow newer upstream `main` engine and launcher-core revisions with recorded compatibility results.

## Exit criteria
- Phase 0 is done when PMCS CI is green, the upstream revision is recorded and the stack decision is recorded.
- Later phases get their own exit criteria when they are planned.

- 2026-11-19: Compose APK build is past patch application and the Rust build; Gradle Java target fixed, next run pending.
