# Issues

Status vocabulary: OPEN, INVESTIGATING, IMPLEMENTED, VERIFIED, BLOCKED.

## I-001 — PMCS CI unverified on RustDeck
Status: VERIFIED (5 consecutive successful runs on main, 2026-11-18)
The workflow and validator were copied and adapted from Rusty-survival-. The first GitHub Actions run on this repository has not been checked.

## I-002 — Scope and stack undecided
Status: OPEN
UI stack, platforms beyond mobile and packaging are UNDECIDED.

## I-003 — Updating engine, launcher core and UI without a new APK is unproven
Status: OPEN
D-007 requires it. Android may limit loading downloaded native code or UI payloads; the exact restrictions and the chosen design are not yet verified on a device. Nothing here is IMPLEMENTED.

## I-004 — Upstream has no Android launcher; licensing of reuse
Status: OPEN
Upstream's launcher is a desktop GPUI app (see UPSTREAM_SYNC.md). "Close to upstream" on Android therefore means reusing/following its Rust logic (version listing, install, signing checks, prepare) while the UI is new. Copying launcher code brings LGPL-3.0-or-later obligations; not yet analyzed. Upstream status (UPSTREAM_SYNC.md): no official Android client; only input pieces exist. Probe results (S-2026-11-18-12): the client does not compile for Android as is; first blockers are the missing winit activity feature (a config fix) and arboard, which has no Android backend (used in one file). Further blockers are unknown. Whether the client runs on Android is unverified and remains the main blocker.

## I-005 — Meaning of "release" vs "nightly" given upstream
Status: VERIFIED (resolved by the user 2026-11-18, see D-008)
Upstream's game ships only Nightly; releases exist only for the launcher (`launcher@X.Y.Z`). D-008's "release" channel needs a precise meaning (probably launcher releases). Needs the user's confirmation.

## I-006 — Game preparation on Android needs Java or a replacement
Status: INVESTIGATING (direction chosen in D-011; unproven)
Upstream preparation needs JDK 25 and the player's own Minecraft files (or Mojang download for an owning account); Java-free preparation is upstream issue #1750. On Android this must be solved or the launcher cannot prepare the game.
