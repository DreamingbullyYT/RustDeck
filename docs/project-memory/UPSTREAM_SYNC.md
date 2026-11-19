# Upstream Sync

## Tracked upstream
- Policy (D-005): follow upstream `main`; no version pinning.
- Upstream repo: https://github.com/horizon-rust/horizon
- Last observed Horizon `main` revision: 2d02b5e1a04c0e2ed224d4f218dc57d64b838aac (2026-11-19; branch head observed, not built)
- Last checked: 2026-11-19

## Divergence from upstream
See the patches table below (D-012). Every intentional divergence must reference a decision in DECISIONS.md.

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

## Android status upstream (checked 2026-11-18 at e3d4dc5; from ROADMAP.md and source)
- No official Android build. ROADMAP.md lists Android as a long-term goal and says touch controls, mobile interface, packaging and loading the player's own assets were missing; some contributor pieces exist.
- Source has Android input code in crates/horizon-client-render (android_runtime.rs, android_gamepad.rs, keypad.rs, touch/), adapted from the Starling `android` branch (MIT). It is input state only; nothing there proves the client runs on Android.
- Resource preparation needs JDK 25 and the player's own Minecraft files (or the launcher's download from Mojang for an owning account). Upstream tracks Java-free preparation in issue #1750. Horizon ships no Mojang files.
- The launcher has a CLI (`horizon-launcher`): versions, install, play (`--instance`, `--version`), prepare (`--download`), and more; reference in docs/launcher-commands.md. Games advertise `--launch-api`.

## Divergence: patches (D-012)
| Patch | What | Status |
|---|---|---|
| patches/upstream/0001-android-key-extensions.patch | Android has no winit `scancode` / `modifier_supplement` extensions: `raw_key_code` returns 0 and `window_handler.rs` uses the logical key on Android (crates/horizon-client-input/src/keybinds.rs, crates/horizon-client/src/game/runtime/window_handler.rs; Android-only cfg) | applies to b45d73d (git apply --check, local); compiles in CI together with the others (cargo check, run 44464658215) |
| patches/upstream/0002-android-dualsense-off.patch | gates crates/horizon-dualsense off on Android exactly as upstream already does for iOS (`target_os = "ios"` becomes `any(ios, android)`; mechanical, 7 files), so hidapi is not built | applies to b45d73d (git apply --check, local); compiles in CI together with the others (cargo check, run 44464658215) |
| patches/upstream/0003-android-no-mod-holder.patch | crates/horizon-client/src/entry.rs and crates/horizon-game/src/lib.rs: the mod runtime holder (`horizon_mod_package::holder`, built only for Linux, macOS, Windows) is skipped on Android (`enter()` not called, no capture). Behavior difference: no holder on Android | applies to b45d73d (git apply --check, local); compiles in CI together with the others (cargo check, run 44464658215) |

Patches 0001-0003 are `cfg`-gated to Android; 0004 changes the call site for all platforms but calls `EventLoop::new()` off Android; desktop code paths are unchanged in behavior (checked by reading the diffs; a desktop build with the patches has not been run).

Removed patches: the earlier winit-activity patch and the arboard/clipboard patch became unnecessary on 2026-11-19, when upstream commit 5cfd9f1 (#18358, "Unblock Android checks for the dedicated engine") added the same fixes (winit `android-native-activity` in crates/horizon-client/Cargo.toml, arboard off Android, `learn_layout` on Android).

| patches/upstream/0004-android-event-loop.patch | new file crates/horizon-client/src/android_app.rs (`event_loop()`: on Android builds the winit loop with the stored AndroidApp, elsewhere `EventLoop::new()`); `pub mod android_app;` in lib.rs; the three `EventLoop::new()` calls in game/runtime/{no_resources,resource_loader,loading_screen}.rs now call it. Desktop behavior is the same call | applies to b45d73d; compiles on Android (APK build run 44468371147) and on Linux (desktop check run 44468287424) |
| patches/upstream/0005-android-guest-stack-startup.patch | Android-only `prepare()` returns `Ok(())` instead of calling unsupported stack inspection and aborting `horizon_game::main()`. Worker stack sizing still uses `builder()`; this is a diagnostic unblock, not proof guest stack safety. | added 2026-11-19; first apply attempt failed due malformed hunk count; corrected in 2c07940; run 44496466298 applies successfully on upstream 2d02b5e1, Android build underway |
