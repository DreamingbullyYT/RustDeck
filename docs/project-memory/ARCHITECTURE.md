# Architecture

Status: PARTIALLY DECIDED. Product/UI direction and UI technology are accepted in DECISIONS.md; implementation architecture, Kotlin-to-Rust bridge, payload packaging and updater internals remain undecided.

## Constraints already fixed
- Launcher for Horizon (Minecraft Java rewritten in Rust).
- Engine version and launcher core track upstream Horizon (PC) as closely as possible.
- UI and flows mirror the official Minecraft launcher, mobile friendly (D-006).
- Upstream `main` is followed with no version pinning (D-005).
- UI: Kotlin with Jetpack Compose (D-010), official-launcher style with animated background, bound to the launcher core (D-009).
- Updates are automatic from this repo; the APK changes only for new permissions; engine, launcher core and UI are updatable without a new APK (D-007).
- Divergence from upstream is minimal and recorded in DECISIONS.md.

## Android app structure (first implementation)
- `app/` is the Kotlin/Jetpack Compose launcher Activity and owns the visible RustDeck UI.
- The Horizon engine remains a separate `android.app.NativeActivity`, with its native Rust library packaged under `app/src/main/jniLibs/arm64-v8a/`.
- The Compose Activity is the APK entry point. The engine Activity is not exposed as the launcher entry point and must only be started after RustDeck has prepared valid game resources (D-014).
- The current Compose screens are a UI foundation only: Home, Installations and Settings; they do not yet invoke the Rust launcher core.

## Still to design / implement
- Kotlin-to-Rust launcher-core bridge (JNI, CLI process or other).
- How Rust core is exposed as an Android library without copying or forking upstream logic unnecessarily.
- Thin bootstrap/updater and independently updateable engine, launcher-core and UI payloads (D-007; I-003 remains unproven).

## CI
- CI builds APK, libraries and other outputs automatically only when upstream has a new release or nightly; no new upstream build means no build (D-008). Not implemented.

## To be decided (record in DECISIONS.md, then describe here)
- Platforms beyond mobile, and distribution format
- Update mechanism, check frequency and payload verification (signing/hashes); release and nightly channels, newest build wins (D-008)
- Which parts live in the APK shell vs updatable payloads
- How the launcher obtains, verifies and updates engine builds
- How the launcher core stays in sync with upstream

## Update 2026-11-19 (D-017)
No upstream launcher core. Kotlin app plus the engine library only; the bullets above about a launcher-core bridge are obsolete.
