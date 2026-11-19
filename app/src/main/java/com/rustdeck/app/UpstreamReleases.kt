package com.rustdeck.app

import java.net.HttpURLConnection
import java.net.URL
import org.json.JSONArray

/** One Horizon game build published upstream (tag, date, whether it ships an Android asset). */
data class UpstreamBuild(val tag: String, val date: String, val hasAndroidAsset: Boolean)

/** Lists game builds from horizon-rust/horizon (launcher@ releases are skipped), newest first. */
object UpstreamReleases {
    private const val URL_LIST = "https://api.github.com/repos/horizon-rust/horizon/releases?per_page=30"

    fun fetch(): Result<List<UpstreamBuild>> = runCatching {
        val conn = URL(URL_LIST).openConnection() as HttpURLConnection
        conn.connectTimeout = 10_000
        conn.readTimeout = 15_000
        conn.setRequestProperty("Accept", "application/vnd.github+json")
        conn.setRequestProperty("User-Agent", "RustDeck")
        try {
            check(conn.responseCode == 200) { "GitHub answered HTTP ${conn.responseCode}" }
            val arr = JSONArray(conn.inputStream.bufferedReader().readText())
            buildList {
                for (i in 0 until arr.length()) {
                    val r = arr.getJSONObject(i)
                    val tag = r.optString("tag_name")
                    if (r.optBoolean("draft") || tag.startsWith("launcher@")) continue
                    val assets = r.optJSONArray("assets")
                    var android = false
                    if (assets != null) for (j in 0 until assets.length()) {
                        if (assets.getJSONObject(j).optString("name").contains("android", true)) android = true
                    }
                    add(UpstreamBuild(tag, r.optString("published_at").take(10), android))
                }
            }
        } finally {
            conn.disconnect()
        }
    }
}
