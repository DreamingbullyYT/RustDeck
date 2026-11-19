#!/usr/bin/env bash
# Packages a NativeActivity library into a signed APK with the Android SDK build tools (no Gradle).
# usage: android/build_apk.sh <librustdeck_android.so> <out.apk> <version-code> <version-name>
set -euo pipefail
SO=$(realpath "$1"); OUT=$(realpath -m "$2"); CODE=$3; NAME=$4
HERE=$(cd "$(dirname "$0")" && pwd)
SDK=${ANDROID_HOME:-${ANDROID_SDK_ROOT:?ANDROID_HOME is not set}}
BT=$(ls -d "$SDK"/build-tools/*/ | sort -V | tail -1)
PLAT=$(ls -d "$SDK"/platforms/android-*/ | sort -V | tail -1)
echo "build-tools: $BT  platform: $PLAT"
WORK=$(mktemp -d)
cp "$HERE/AndroidManifest.xml" "$WORK/"
mkdir -p "$WORK/lib/arm64-v8a"
cp "$SO" "$WORK/lib/arm64-v8a/librustdeck_android.so"
cd "$WORK"
"$BT/aapt2" link -o base.apk --manifest AndroidManifest.xml -I "$PLAT/android.jar" \
  --min-sdk-version 26 --target-sdk-version 34 --version-code "$CODE" --version-name "$NAME"
zip -q -r base.apk lib
"$BT/zipalign" -f -p 4 base.apk aligned.apk
"$BT/apksigner" sign --ks "$HERE/debug.keystore" --ks-type pkcs12 --ks-pass pass:android \
  --key-pass pass:android --ks-key-alias androiddebugkey --out "$OUT" aligned.apk
"$BT/apksigner" verify --verbose "$OUT" | head -5
ls -l "$OUT"
