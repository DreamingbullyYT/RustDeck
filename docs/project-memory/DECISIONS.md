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

## D-011 — Prototype: bundle Java in the APK; move to Java-free preparation when upstream has it
Date: 2026-11-18
Status: ACCEPTED
For game preparation on Android, the prototype bundles a JDK 25 build for Android/ARM64 with the app and runs it as a process. When upstream delivers Java-free preparation (upstream issue #1750), RustDeck switches to it. Not verified: that a JDK 25 for Android exists, its size, and that Android allows running it (executables may have to be packaged as native libraries). Writing our own Rust replacement of the vanilla data generator is not planned.

## D-012 — Upstream changes live as small patch files in this repo
Date: 2026-11-18
Status: ACCEPTED
Changes RustDeck needs in upstream Horizon (engine or launcher core) are kept as small patch files in patches/upstream/, applied in CI to a fresh checkout of upstream `main`, and new code goes in separate files where possible, so upstream files change as little as possible (D-002). Fixes that make sense for everyone are offered upstream as PRs; once merged, the patch is deleted. Patches that stop applying after an upstream change must be refreshed, and that is recorded in UPSTREAM_SYNC.md.

## D-013 — First APK is a test shell; published as the `nightly` prerelease
Date: 2026-11-19
Status: ACCEPTED
RustDeck's first APK is a NativeActivity shell (android/shell, copied into the upstream checkout as crates/rustdeck-android by CI) that starts upstream's `horizon_game::main`. It is built by the manual workflow android-apk.yml from upstream `main` plus patches/upstream/, signed with a committed DEBUG key (android/debug.keystore, password `android`) and published as the `nightly` GitHub prerelease (D-008). It is a test build for the phone, not the launcher UI (D-009/D-010). It writes its log to <app external files dir>/rustdeck.log and logcat (tag RustDeck). Before any public release the signing key must be replaced by a secret one (I-008).
