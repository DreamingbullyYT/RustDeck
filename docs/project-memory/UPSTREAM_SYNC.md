# Upstream Sync

## Tracked upstream
- Policy (D-005): follow upstream `main`; no version pinning.
- Upstream repo: https://github.com/horizon-rust/horizon
- Last observed Horizon `main` revision: e3d4dc50dd14eea839eba72cdacd3426976e5fd3 (2026-11-18, inspected only; no build used it)
- Last checked: 2026-11-18

## Divergence from upstream
None recorded. Every intentional divergence must reference a decision in DECISIONS.md.

## Sync procedure
1. Fetch the latest upstream `main`.
2. Record the observed revision here (an observation, not a pin).
3. Build and test against it; record results in COMPATIBILITY.md.
4. Resolve breakage, keeping divergence minimal.
5. Update CHANGELOG.md and append to SESSION_LOG.md.

## Observed upstream facts (2026-11-18, from upstream source and docs; re-check before relying on them)
- Horizon rebuilds Minecraft Java 26.3 in Rust; game MIT, launcher LGPL-3.0-or-later (launcher/Cargo.toml).
- Since 2026-10-28 the game ships only a **Nightly** (docs/release-flow.md). Tags: `nightly`, or `nightly-<date>` for the last week's; the build installs as `nightly-<date>-<commit>`. Builds are signed. No `vX.Y.Z` game releases are made any more.
- The launcher has its own releases, tagged `launcher@X.Y.Z` (latest seen on the releases page: 0.2.58, 9 Nov).
- The nightly train (`release` workflow) has run only by hand since 2026-11-15 (comment in .github/workflows/release.yml): no automatic Nightly on merges or timer.
- Upstream launcher is a desktop app (Linux, macOS, Windows x64 and ARM64) in Rust with GPUI Fast; it lists builds, installs them, prepares game files from the player's own Minecraft install or downloads them from Mojang for an account that owns the game, and starts the client. No Android launcher exists upstream; docs/layers.md only mentions iOS/Android as possible desktop-class ports.
- Upstream release signing crate: crates/horizon-release-sign. Launcher self-update: tar.gz/AppImage/zip (launcher/src/backend.rs).
