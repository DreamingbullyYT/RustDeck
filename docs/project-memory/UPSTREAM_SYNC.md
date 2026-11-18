# Upstream Sync

## Tracked upstream
- Policy (D-005): follow upstream `main`; no version pinning.
- Last observed Horizon `main` revision used by a build: none yet
- Last checked: never

## Divergence from upstream
None recorded. Every intentional divergence must reference a decision in DECISIONS.md.

## Sync procedure
1. Fetch the latest upstream `main`.
2. Record the observed revision here (an observation, not a pin).
3. Build and test against it; record results in COMPATIBILITY.md.
4. Resolve breakage, keeping divergence minimal.
5. Update CHANGELOG.md and append to SESSION_LOG.md.
