package com.rustdeck.app

import android.content.Context
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest
import org.json.JSONObject

/** Engine payload published by the payload-sync workflow (D-015). */
data class EnginePayload(val file: String, val sha256: String, val size: Long, val upstreamSha: String, val upstreamDate: String)

sealed interface UpdateResult {
    data class UpToDate(val installed: EnginePayload) : UpdateResult
    data class Updated(val installed: EnginePayload) : UpdateResult
    data class Failed(val message: String) : UpdateResult
}

/** Downloads the engine library when the published one is newer (by upstream date) than the installed one. */
object PayloadUpdater {
    private const val BASE = "https://github.com/DreamingbullyYT/RustDeck/releases/download/payloads/"

    private fun dir(ctx: Context) = File(ctx.filesDir, "payloads").apply { mkdirs() }

    fun installed(ctx: Context): EnginePayload? = runCatching {
        parse(JSONObject(File(dir(ctx), "engine.json").readText()))
    }.getOrNull()

    private fun parse(o: JSONObject) = EnginePayload(
        o.getString("file"), o.getString("sha256"), o.getLong("size"),
        o.getString("upstream_sha"), o.getString("upstream_date"),
    )

    private fun open(url: String): HttpURLConnection =
        (URL(url).openConnection() as HttpURLConnection).apply {
            connectTimeout = 15_000; readTimeout = 60_000
            setRequestProperty("User-Agent", "RustDeck")
        }

    fun check(ctx: Context, onProgress: (String) -> Unit = {}): UpdateResult = try {
        val m = open(BASE + "manifest.json").run {
            check(responseCode == 200) { "manifest: HTTP $responseCode" }
            try { JSONObject(inputStream.bufferedReader().readText()) } finally { disconnect() }
        }
        val remote = parse(m.getJSONObject("engine"))
        val local = installed(ctx)
        if (local != null && local.upstreamDate >= remote.upstreamDate && local.sha256 == remote.sha256) {
            UpdateResult.UpToDate(local)
        } else if (local != null && local.upstreamDate > remote.upstreamDate) {
            UpdateResult.UpToDate(local)
        } else {
            val tmp = File(dir(ctx), "engine.download")
            val digest = MessageDigest.getInstance("SHA-256")
            open(BASE + remote.file).run {
                check(responseCode == 200) { "engine: HTTP $responseCode" }
                try {
                    inputStream.use { i -> tmp.outputStream().use { o ->
                        val buf = ByteArray(64 * 1024)
                        var done = 0L
                        var shown = -1L
                        while (true) {
                            val n = i.read(buf); if (n < 0) break
                            digest.update(buf, 0, n); o.write(buf, 0, n)
                            done += n
                            if (done / 1_000_000 != shown) { shown = done / 1_000_000; onProgress("Downloading engine… ${shown} / ${remote.size / 1_000_000} MB") }
                        }
                    } }
                } finally { disconnect() }
            }
            val sum = digest.digest().joinToString("") { "%02x".format(it) }
            if (sum != remote.sha256) { tmp.delete(); error("engine: sha256 mismatch") }
            val target = File(dir(ctx), remote.file)
            check(tmp.renameTo(target)) { "could not store engine" }
            target.setReadOnly()
            File(dir(ctx), "engine.json").writeText(m.getJSONObject("engine").toString())
            UpdateResult.Updated(remote)
        }
    } catch (e: Exception) {
        UpdateResult.Failed(e.message ?: e.javaClass.simpleName)
    }
}
