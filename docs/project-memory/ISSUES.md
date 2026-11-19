# Issues

Status vocabulary: OPEN, INVESTIGATING, IMPLEMENTED, VERIFIED, BLOCKED.

## I-001 — PMCS CI unverified on RustDeck
Status: VERIFIED (5 consecutive successful runs on main, 2026-11-18)
The workflow and validator were copied and adapted from Rusty-survival-. The first GitHub Actions run on this repository has not been checked.

## I-002 — Remaining scope and packaging decisions
Status: OPEN
The mobile UI stack is now decided as Kotlin + Jetpack Compose (D-010), and the first app/engine Activity split is recorded in D-014. Platforms beyond Android/mobile, final packaging, the Kotlin-to-Rust bridge and the D-007 independently updateable payload design remain undecided.

## I-003 — Updating engine, launcher core and UI without a new APK is unproven
Status: OPEN
D-007 requires it. Android may limit loading downloaded native code or UI payloads; the exact restrictions and the chosen design are not yet verified on a device. Nothing here is IMPLEMENTED.

## I-004 — Upstream has no Android launcher; licensing of reuse
Status: OPEN
Upstream's launcher is a desktop GPUI app (see UPSTREAM_SYNC.md). "Close to upstream" on Android therefore means reusing/following its Rust logic (version listing, install, signing checks, prepare) while the UI is new. Copying launcher code brings LGPL-3.0-or-later obligations; not yet analyzed. Result 2026-11-19 (run 44464658215): with upstream b45d73d plus our three small patches, `cargo check -p horizon-client --target aarch64-linux-android` SUCCEEDS (type-checks; not linked, not run). Upstream status (UPSTREAM_SYNC.md): no official Android client; only input pieces exist. Probe results (S-2026-11-18-12): the client does not compile for Android as is; first blockers are the missing winit activity feature (a config fix) and arboard, which has no Android backend (used in one file). Run 3 (patches 0001/0002): next blockers are winit desktop-only APIs in keybinds.rs/window_handler.rs and the hidapi build (libudev). Further blockers are unknown. Whether the client runs on Android is unverified and remains the main blocker.

## I-005 — Meaning of "release" vs "nightly" given upstream
Status: VERIFIED (resolved by the user 2026-11-18, see D-008)
Upstream's game ships only Nightly; releases exist only for the launcher (`launcher@X.Y.Z`). D-008's "release" channel needs a precise meaning (probably launcher releases). Needs the user's confirmation.

## I-006 — Game preparation on Android needs Java or a replacement
Status: INVESTIGATING (direction chosen in D-011; unproven)
Upstream preparation needs JDK 25 and the player's own Minecraft files (or Mojang download for an owning account); Java-free preparation is upstream issue #1750. On Android this must be solved or the launcher cannot prepare the game.

## I-007 — Patches break when upstream moves
Status: INVESTIGATING
Upstream `main` changes daily (D-005). On 2026-11-19 two patches stopped applying and the probe workflow did not fail on it, so its result was misleading; the workflow now fails on a patch that does not apply. Upstream also started Android work itself (5cfd9f1), which makes patches obsolete: re-check upstream before writing new ones.

## I-008 — APK signed with a committed debug key
Status: OPEN
android/debug.keystore is in the repository and its password is public. Anyone can sign an APK that Android treats as an update of ours. Fine for a private test build; must be replaced by a key kept as a GitHub secret before updates (D-007) or any public distribution. Changing the key later means users must uninstall first.

## I-009 — Upstream opens several event loops one after another
Status: OPEN (the APK builds; the first phone test will show it)
Upstream creates a new winit event loop for the resource screen, the loading screen and the game (crates/horizon-client/src/game/runtime/no_resources.rs, resource_loader.rs, loading_screen.rs). On Android winit may allow only one event loop per process, and a NativeActivity cannot restart its loop. Not verified; if confirmed, the game needs a single-loop path on Android (an engine change, to be offered upstream).


## I-010 — Android guest-stack startup check exits before game initialization
Status: PARTIALLY VERIFIED — workaround reaches Horizon UI
The first device log showed `GuestStackUnavailable: stack inspection is unsupported on this platform`. Patch 0005 skips the fatal guest-stack check on Android. The user then reached Horizon's `No prepared game files` UI, verifying startup progresses past this check. This does not prove guest stack guarantees or `check_current()` behavior are safe for guest execution; those remain unresolved and require a real game launch test.


## I-011 — Reuse of Horizon launcher core on Android and Android engine artifacts
Status: INVESTIGATING
Upstream has a shared launcher library in `launcher/src/lib.rs` (documented as the core shared by GUI, CLI and MCP) and public backend functions for releases, install, installed versions and preparation. This is preferable to reimplementing those flows, but Android reuse is not yet proven. The `launcher/Cargo.toml` includes desktop GPUI dependencies, so the library's Android dependency graph must be checked without building the desktop binary/UI. Also, `backend::PLATFORMS` currently lists only Linux x86_64, macOS x86_64/ARM64 and Windows x86_64/ARM64; there is no Android asset. RustDeck needs its own ARM64 engine artifact/update metadata and verification path. The native engine is a NativeActivity library, not an executable that the current desktop `backend::play` process path can launch.


## I-012 — Android compilation: holder-dependent runtime lease helper is not cfg-gated
Status: PATCHED — build verification pending
Android excludes `horizon-mod-package::holder`, but upstream `runtime_leases::lease_units()` called `crate::holder::lease_paths()` unconditionally when `runtime-process` is enabled. Build run 44513128759 failed with E0433 and a derivative E0277. RustDeck patch 0006 gates `lease_units` to Linux/macOS/Windows, matching holder availability. This helper is not used on Android in current shell. Verify patch application and compile in next run; keep this narrow and upstreamable.

## I-013 — Hand-written patch hunks failed to apply
Status: INVESTIGATING
Patch 0006 failed twice at `git apply` (wrong hunk counts, then context that did not match upstream) before it applied. Lesson: create patches with `git diff` from a real checkout of upstream and run `git apply --check` before pushing, never write hunks by hand.

## I-014 — Unauthenticated GitHub API limit
Status: OPEN
The app lists upstream releases without a token (60 requests per hour per IP). Upstream's launcher reads through a mirror past the limit (crate::github); RustDeck needs caching and a fallback, ideally from its own repo.
