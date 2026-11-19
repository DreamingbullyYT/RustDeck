# Changelog

## 2026-11-18 — Bootstrap PMCS
- Change ID: C-2026-11-18-01
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: Need durable project memory that survives context compression, as in Rusty-survival-.
- Root cause: New repository with no continuity system.
- Files/modules affected: docs/project-memory/*, scripts/validate_project_memory.py, tests/test_validate_project_memory.py, .github/workflows/project-memory.yml
- Implementation: Copied the PMCS from Rusty-survival- and adapted it for RustDeck. Replaced PARITY_MATRIX and REUSE_MATRIX with UPSTREAM_SYNC. Updated the validator's required files and markers.
- Tests/commands: see Exact results
- Exact results: validator PASSED; 4 regression tests OK (local). See SESSION_LOG.md entry S-2026-11-18-01
- Not tested: GitHub Actions run on this repository
- Risks / follow-up: Verify CI is green (I-001).

## 2026-11-18 — Upstream-main tracking and official-launcher UI decisions
- Change ID: C-2026-11-18-02
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: User decided to follow upstream `main` without pinning and to mirror the official Minecraft launcher, mobile friendly.
- Root cause: Earlier docs assumed pinned upstream revisions.
- Files/modules affected: docs/project-memory/* (AI_HANDOFF, PROJECT_MEMORY, CURRENT_STATE, ARCHITECTURE, DECISIONS, ISSUES, ROADMAP, COMPATIBILITY, UPSTREAM_SYNC), scripts/validate_project_memory.py
- Implementation: Added D-005 and D-006; replaced pinning wording with observed-revision wording; validator marker "Pinned upstream" became "Tracked upstream".
- Tests/commands: python scripts/validate_project_memory.py; python -m unittest discover -s tests
- Exact results: validator PASSED; 4 regression tests OK (local). See SESSION_LOG.md entry S-2026-11-18-02
- Not tested: GitHub Actions run
- Risks / follow-up: Without pinning, builds are not reproducible; record the observed upstream revision per build.

## 2026-11-18 — Auto-update decision
- Change ID: C-2026-11-18-03
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: User wants newest engine and launcher always, updated automatically from our repo, with an APK only for new permissions and an updatable UI.
- Root cause: Update model was not yet recorded.
- Files/modules affected: docs/project-memory/* (DECISIONS, ARCHITECTURE, CURRENT_STATE, ISSUES, ROADMAP, AI_HANDOFF)
- Implementation: Added D-007 and I-003; recorded the implied APK-shell plus updatable-payload structure as not yet designed.
- Tests/commands: python scripts/validate_project_memory.py; python -m unittest discover -s tests
- Exact results: see SESSION_LOG.md entry S-2026-11-18-03
- Not tested: GitHub Actions run; any update mechanism on a device
- Risks / follow-up: Prove update-without-APK on Android (I-003).

## 2026-11-18 — No update channels
- Change ID: C-2026-11-18-04
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: User does not care about the update channel; the newest build should be installed.
- Root cause: Channel policy was left open.
- Files/modules affected: docs/project-memory/* (DECISIONS, ARCHITECTURE, CURRENT_STATE)
- Implementation: Added D-008; the mechanism stays an implementation detail.
- Tests/commands: python scripts/validate_project_memory.py; python -m unittest discover -s tests
- Exact results: validator PASSED; tests OK (local)
- Not tested: GitHub Actions run
- Risks / follow-up: Newest-wins with no channels means a bad build reaches everyone; consider this when designing the updater.

## 2026-11-18 — Correct D-008: release and nightly channels
- Change ID: C-2026-11-18-05
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: C-2026-11-18-04 misread the user; they meant release vs nightly channels, not "no channels".
- Root cause: Misunderstanding of "update channel".
- Files/modules affected: docs/project-memory/* (DECISIONS, ARCHITECTURE, CURRENT_STATE)
- Implementation: Rewrote D-008 to two channels, release and nightly; nightly CI builds APK, libs and other outputs automatically when upstream has no new release.
- Tests/commands: python scripts/validate_project_memory.py; python -m unittest discover -s tests
- Exact results: validator PASSED; tests OK (local)
- Not tested: GitHub Actions run; no nightly workflow exists yet
- Risks / follow-up: Open: whether the player picks a channel; whether nightly is skipped whenever a release exists.

## 2026-11-18 — Correct D-008 again: build only on new upstream builds
- Change ID: C-2026-11-18-06
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: C-2026-11-18-05 had the nightly trigger backwards; the user wants no build when upstream has no new nightly/release.
- Root cause: Misread the trigger condition.
- Files/modules affected: docs/project-memory/* (DECISIONS, ARCHITECTURE, CURRENT_STATE)
- Implementation: Rewrote D-008: builds happen only when upstream publishes a new release or nightly.
- Tests/commands: python scripts/validate_project_memory.py; python -m unittest discover -s tests
- Exact results: validator PASSED; tests OK (local)
- Not tested: GitHub Actions run; no CI build workflow exists yet
- Risks / follow-up: Open: how CI detects new upstream builds; whether the player picks a channel.

## 2026-11-18 — Upstream investigation recorded
- Change ID: C-2026-11-18-07
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: Needed to know how upstream Horizon publishes releases/nightlies and what its launcher is.
- Root cause: n/a (research)
- Files/modules affected: docs/project-memory/UPSTREAM_SYNC.md, ISSUES.md, CURRENT_STATE.md
- Implementation: Read upstream README, release page, sparse checkout of launcher/, .github/workflows, docs. Recorded findings and open issues I-004, I-005.
- Tests/commands: python scripts/validate_project_memory.py
- Exact results: validator PASSED (local)
- Not tested: anything runnable; GitHub web tree pages were blocked by robots, so launcher/workflow details came from a sparse git checkout at e3d4dc5
- Risks / follow-up: Facts are from one snapshot; re-check.

## 2026-11-18 — Release/nightly meaning and UI direction
- Change ID: C-2026-11-18-08
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: User confirmed release = stable launcher releases, nightly = newest engine; UI like the official Mojang launcher with animated background, bound to launcher core.
- Root cause: n/a (decisions)
- Files/modules affected: docs/project-memory/* (DECISIONS, ISSUES, ARCHITECTURE, CURRENT_STATE)
- Implementation: Refined D-008, added D-009, closed I-005.
- Tests/commands: python scripts/validate_project_memory.py
- Exact results: validator PASSED (local)
- Not tested: nothing runnable exists yet
- Risks / follow-up: I-002 (UI technology), I-004 (Android client, LGPL).

## 2026-11-18 — Android status upstream and Compose decision
- Change ID: C-2026-11-18-09
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: Needed to know whether Horizon's client can run on Android, and the user chose Kotlin with Compose.
- Root cause: n/a (research and decision)
- Files/modules affected: docs/project-memory/* (UPSTREAM_SYNC, DECISIONS, ISSUES, ARCHITECTURE, CURRENT_STATE)
- Implementation: Recorded upstream Android status and CLI; added D-010 and I-006; updated I-004.
- Tests/commands: python scripts/validate_project_memory.py
- Exact results: validator PASSED (local)
- Not tested: nothing run on Android; findings come from reading upstream source and ROADMAP at e3d4dc5
- Risks / follow-up: Verify the client on a real device; decide the Kotlin-to-Rust bridge.

## 2026-11-18 — Java bundled for the prototype
- Change ID: C-2026-11-18-10
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: Game preparation on Android needs Java (I-006).
- Root cause: n/a (decision)
- Files/modules affected: docs/project-memory/* (DECISIONS, ISSUES, CURRENT_STATE)
- Implementation: Added D-011; I-006 now INVESTIGATING; added the first test to next actions.
- Tests/commands: python scripts/validate_project_memory.py
- Exact results: validator PASSED (local)
- Not tested: no JDK for Android checked; nothing run on a device
- Risks / follow-up: JDK 25 availability, size and Android execution limits.

## 2026-11-18 — Android client probe workflow
- Change ID: C-2026-11-18-11
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: Test whether upstream's client compiles for Android before building the launcher UI (I-004).
- Root cause: n/a (experiment)
- Files/modules affected: .github/workflows/android-client-probe.yml, docs/project-memory/* (CURRENT_STATE, ISSUES)
- Implementation: Manual workflow that checks out upstream main and runs cargo check for aarch64-linux-android on horizon-client-render and horizon-client with the NDK on the runner. Closed I-001 (CI green).
- Tests/commands: python scripts/validate_project_memory.py
- Exact results: validator PASSED (local); workflow run result recorded in SESSION_LOG S-2026-11-18-11
- Not tested: a compile check does not show the client starts on a device
- Risks / follow-up: winit may need an Android activity feature; the client may not be buildable as an Android app without upstream changes.

## 2026-11-18 — Android probe: winit feature step
- Change ID: C-2026-11-18-12
- Commit: 69d958b
- Status: IMPLEMENTED
- Motivation / observed problem: Probe run 1 failed because winit's android-activity had no activity feature.
- Root cause: Upstream's winit dependency has no Android activity feature enabled.
- Files/modules affected: .github/workflows/android-client-probe.yml, docs/project-memory/* (SESSION_LOG, ISSUES, CURRENT_STATE)
- Implementation: Probe-only step runs `cargo add winit --features android-native-activity` on the CI checkout.
- Tests/commands: workflow runs 44375047842 and 44375791519; python scripts/validate_project_memory.py
- Exact results: both runs failed in dependencies (android-activity, then arboard); see SESSION_LOG S-2026-11-18-12
- Not tested: anything past the first failing dependency; any device
- Risks / follow-up: More failures likely behind arboard.

## 2026-11-18 — Upstream patches as files; probe applies them
- Change ID: C-2026-11-18-13
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: Keep upstream changes minimal and separate (D-002, user request); the probe's `cargo add` step was not reusable.
- Root cause: n/a
- Files/modules affected: patches/upstream/0001-*.patch, patches/upstream/0002-*.patch, .github/workflows/android-client-probe.yml, docs/project-memory/*
- Implementation: Two patches (winit Android activity feature; arboard off Android with a stand-in module in a new file). The workflow checks out RustDeck and upstream side by side, applies the patches, then runs cargo check for aarch64-linux-android.
- Tests/commands: git apply --check of both patches against upstream 8989a51; python scripts/validate_project_memory.py
- Exact results: both patches apply cleanly (local); validator PASSED. CI result: see SESSION_LOG S-2026-11-18-13
- Not tested: the patched code has not been compiled yet; nothing on a device
- Risks / follow-up: More errors likely behind arboard.

## 2026-11-18 — Android probe run 3 recorded
- Change ID: C-2026-11-18-14
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: Check whether patches 0001 and 0002 move the Android build forward.
- Root cause: Next blockers are in upstream's own input code (winit desktop-only APIs) and the hidapi build.
- Files/modules affected: docs/project-memory/* (UPSTREAM_SYNC, ISSUES, CURRENT_STATE, SESSION_LOG)
- Implementation: documentation only.
- Tests/commands: workflow run 44377119048
- Exact results: see SESSION_LOG S-2026-11-18-14
- Not tested: past the first failing crates; any device
- Risks / follow-up: Patches 0003 and 0004 touch upstream engine code; keep them minimal (D-012).

## 2026-11-19 — Patches 0003 and 0004
- Change ID: C-2026-11-19-01
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: Probe run 3 failed on winit desktop-only key APIs and on hidapi (S-2026-11-18-14).
- Root cause: Upstream code assumes desktop winit and a hidapi backend.
- Files/modules affected: patches/upstream/0003-*.patch, 0004-*.patch, docs/project-memory/UPSTREAM_SYNC.md, CURRENT_STATE.md
- Implementation: 0003 adds android_keys.rs and Android-only `use` lines; 0004 extends upstream's iOS gating of horizon-dualsense to Android. All Android-gated.
- Tests/commands: git apply --check of all four patches against upstream 8989a51 (and applied together); python scripts/validate_project_memory.py
- Exact results: all four apply cleanly, together and one by one (local); validator result in SESSION_LOG
- Not tested: compiling the patched code; desktop build with the patches
- Risks / follow-up: 0004 touches many lines (mechanical); more errors likely behind these.

## 2026-11-19 — Patches refreshed to upstream b45d73d
- Change ID: C-2026-11-19-02
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: Run 4 of the probe (44463236029) applied only some patches because upstream moved; the workflow ignored the failures.
- Root cause: Upstream commit 5cfd9f1 (#18358) fixed winit-activity, arboard and learn_layout for Android itself; the loop in the workflow did not stop on a failed patch.
- Files/modules affected: patches/upstream/* (now 0001 keys, 0002 dualsense), .github/workflows/android-client-probe.yml, docs/project-memory/*
- Implementation: Dropped the obsolete patches, rewrote the key patch without a new file, kept the dualsense patch, made the workflow stop on a patch failure and check only horizon-client.
- Tests/commands: git apply --check and applying both patches together on b45d73d; python scripts/validate_project_memory.py
- Exact results: both patches apply (local); CI result in SESSION_LOG
- Not tested: compile; desktop build; any device
- Risks / follow-up: I-007.

## 2026-11-19 — Patch 0003: no mod holder on Android
- Change ID: C-2026-11-19-03
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: Run 5 failed on `horizon_mod_package::holder` in entry.rs.
- Root cause: Upstream builds the holder only for Linux, macOS and Windows.
- Files/modules affected: patches/upstream/0003-android-no-mod-holder.patch, docs/project-memory/*
- Implementation: Android-gated skip of holder::enter() and acquire() in entry.rs.
- Tests/commands: git apply --check and applying all three patches together on b45d73d; python scripts/validate_project_memory.py
- Exact results: applies (local); CI result in a later SESSION_LOG entry
- Not tested: compile; behavior without the holder
- Risks / follow-up: the holder isolates the mod runtime; whether Android needs an equivalent is open.

## 2026-11-19 — Android cargo check passes
- Change ID: C-2026-11-19-04
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: Whether upstream's client compiles for Android with minimal changes (I-004).
- Root cause: n/a (result)
- Files/modules affected: docs/project-memory/* only
- Implementation: documentation of the probe result.
- Tests/commands: workflow run 44464658215 (horizon-rust/horizon b45d73d + patches/upstream 0001-0003, rustc 1.100.0, NDK on ubuntu-latest, `cargo check --target aarch64-linux-android -p horizon-client`)
- Exact results: `cargo check` finished successfully in 5m 00s
- Not tested: linking, a release build, running on a device, desktop build with the patches
- Risks / follow-up: a passing type check says nothing about whether the client starts on Android (windowing, graphics, assets, the holder skipped).

## 2026-11-19 — First APK pipeline and desktop check
- Change ID: C-2026-11-19-05
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED
- Motivation / observed problem: The user has no PC and wants an APK now; desktop must stay unchanged.
- Root cause: n/a
- Files/modules affected: android/ (shell crate, manifest, build_apk.sh, debug.keystore), .github/workflows/android-apk.yml, desktop-check.yml, patches/upstream/0003 (extended to horizon-game) and 0004 (new), docs/project-memory/*
- Implementation: Shell crate with android_main, log to file and logcat, calls horizon_game::main; APK packaged with aapt2/zipalign/apksigner; published to the `nightly` prerelease. Desktop `cargo check` workflow.
- Tests/commands: git apply --check and applying patches 0001-0004 together on b45d73d; bash -n on build_apk.sh; python scripts/validate_project_memory.py
- Exact results: patches apply (local); workflow results in SESSION_LOG
- Not tested: compile of the shell; APK install or start; desktop check
- Risks / follow-up: I-008, I-009; the game may not start on Android.

## 2026-11-19 — Fix patch 0004 import (found by the desktop check)
- Change ID: C-2026-11-19-06
- Commit: d26dbf2
- Status: IMPLEMENTED
- Motivation / observed problem: Desktop `cargo check` and the first APK build failed with E0603 in the new android_app.rs.
- Root cause: `EventLoopError` is private in `winit::event_loop`; it is public in `winit::error`.
- Files/modules affected: patches/upstream/0004-android-event-loop.patch
- Implementation: import `winit::{error::EventLoopError, event_loop::EventLoop}`.
- Tests/commands: git apply --check and applying patches 0001-0004 together on b45d73d; runs 44466410937 (failed before the fix), 44468287424 (desktop) and 44468371147 (APK) after it.
- Exact results: patches apply (local); the two runs after the fix are recorded in SESSION_LOG when they finish.
- Not tested: n/a until those runs finish
- Risks / follow-up: none new.

## 2026-11-19 — First APK published
- Change ID: C-2026-11-19-07
- Commit: pending (replace with resulting commit SHA)
- Status: IMPLEMENTED (the APK builds; it is NOT VERIFIED to run)
- Motivation / observed problem: The user has no PC and asked for an APK.
- Root cause: n/a
- Files/modules affected: docs/project-memory/* (results only)
- Implementation: documentation of runs 44468287424 (desktop) and 44468371147 (APK).
- Tests/commands: the two workflow runs
- Exact results: desktop check SUCCESS; APK built and signed, 50.9 MB, published to the `nightly` prerelease
- Not tested: install, start, logs on a device
- Risks / follow-up: I-008 (debug key), I-009 (event loops); read rustdeck.log from the first start.


## 2026-11-19 — Diagnose blank startup and add Android guest-stack bypass experiment
- Change ID: C-2026-11-19-08
- Commit: 33618a8
- Status: IMPLEMENTED (patch committed; not tested)
- Motivation / observed problem: User's first APK launch showed a blank dark screen; actual log contained `GuestStackUnavailable: stack inspection is unsupported on this platform`.
- Root cause: `horizon_game::main()` calls `horizon_guest_stack::prepare_main()`, which exits process code 1 if platform stack inspection returns an error. Android is excluded from the supported cfg branches.
- Files/modules affected: patches/upstream/0005-android-guest-stack-startup.patch, docs/project-memory/*
- Implementation: Android-specific `prepare()` returns success instead of fatal exit; no behavior changed for other platforms. Guest-stack safety remains an open concern.
- Tests/commands: source diagnosis only; no patch apply, build, or test yet.
- Exact results: root cause matches the log; patch added.
- Not tested: whether 0005 applies to current upstream, APK compilation, launch, guest entry or stack safety.
- Risks / follow-up: I-010. Rebuild and device-test; if guest stack is still inadequate, design a real Android-compatible stack strategy rather than suppressing checks.
