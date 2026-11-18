# Issues

Status vocabulary: OPEN, INVESTIGATING, IMPLEMENTED, VERIFIED, BLOCKED.

## I-001 — PMCS CI unverified on RustDeck
Status: OPEN
The workflow and validator were copied and adapted from Rusty-survival-. The first GitHub Actions run on this repository has not been checked.

## I-002 — Scope and stack undecided
Status: OPEN
UI stack, platforms beyond mobile and packaging are UNDECIDED.

## I-003 — Updating engine, launcher core and UI without a new APK is unproven
Status: OPEN
D-007 requires it. Android may limit loading downloaded native code or UI payloads; the exact restrictions and the chosen design are not yet verified on a device. Nothing here is IMPLEMENTED.

## I-004 — Upstream has no Android launcher; licensing of reuse
Status: OPEN
Upstream's launcher is a desktop GPUI app (see UPSTREAM_SYNC.md). "Close to upstream" on Android therefore means reusing/following its Rust logic (version listing, install, signing checks, prepare) while the UI is new. Copying launcher code brings LGPL-3.0-or-later obligations; not yet analyzed. Also unverified: whether Horizon's client itself can run on Android.

## I-005 — Meaning of "release" vs "nightly" given upstream
Status: VERIFIED (resolved by the user 2026-11-18, see D-008)
Upstream's game ships only Nightly; releases exist only for the launcher (`launcher@X.Y.Z`). D-008's "release" channel needs a precise meaning (probably launcher releases). Needs the user's confirmation.
