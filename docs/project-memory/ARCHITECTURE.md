# Architecture

Status: UNDECIDED. No launcher architecture has been chosen yet.

## Constraints already fixed
- Launcher for Horizon (Minecraft Java rewritten in Rust).
- Engine version and launcher core track upstream Horizon (PC) as closely as possible.
- UI and flows mirror the official Minecraft launcher, mobile friendly (D-006).
- Upstream `main` is followed with no version pinning (D-005).
- UI: official-launcher style with animated background, bound to the launcher core (D-009).
- Updates are automatic from this repo; the APK changes only for new permissions; engine, launcher core and UI are updatable without a new APK (D-007).
- Divergence from upstream is minimal and recorded in DECISIONS.md.

## Implied structure (not yet designed)
- A thin APK shell (permissions, bootstrap, updater) plus updatable payloads: engine, launcher core and UI.

## CI
- CI builds APK, libraries and other outputs automatically only when upstream has a new release or nightly; no new upstream build means no build (D-008). Not implemented.

## To be decided (record in DECISIONS.md, then describe here)
- UI stack
- Platforms beyond mobile, and distribution format
- Update mechanism, check frequency and payload verification (signing/hashes); release and nightly channels, newest build wins (D-008)
- Which parts live in the APK shell vs updatable payloads
- How the launcher obtains, verifies and updates engine builds
- How the launcher core stays in sync with upstream
