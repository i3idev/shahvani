package com.shahvani.app.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Fetches and caches the CSRF token required for mutation requests.
 *
 * Uses the `@Named("plain")` [OkHttpClient] (no CSRF interceptor) to avoid a
 * circular Hilt dependency: the main [OkHttpClient] includes a CSRF interceptor
 * that reads the token from this class, so this class must not depend on the
 * main client.
 */
@Singleton
class CsrfTokenManager @Inject constructor(
    @Named("plain") private val okHttpClient: OkHttpClient
) {
    @Volatile private var cachedToken: String? = null

    /**
     * Returns the cached token if present, otherwise fetches it from the server.
     * Thread-safe: multiple coroutines may call this concurrently; only one fetch
     * is needed because the result is stored in [cachedToken].
     */
    suspend fun getOrFetchToken(): Result<String> {
        cachedToken?.let { return Result.success(it) }
        return fetchToken()
    }

    /**
     * Always fetches a fresh CSRF token from the server and updates the cache.
     * Call this if a mutation request fails with HTTP 403 (token expired/invalid).
     */
    suspend fun fetchToken(): Result<String> = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url("${ApiClient.BASE_URL}/")
                .get()
                .build()

            val response = okHttpClient.newCall(request).execute()
            if (!response.isSuccessful) {
                return@withContext Result.failure(
                    Exception("Failed to fetch CSRF token: HTTP ${response.code}")
                )
            }

            val html = response.body?.string()
                ?: return@withContext Result.failure(Exception("Empty response body"))

            val token = Jsoup.parse(html)
                .selectFirst("meta[name=csrf-token]")
                ?.attr("content")
                ?: return@withContext Result.failure(Exception("CSRF token not found in page"))

            cachedToken = token
            Result.success(token)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /** Returns the currently cached token, or null if none has been fetched yet. */
    fun getToken(): String? = cachedToken

    /** Invalidates the cached token (e.g. after logout). */
    fun clearToken() {
        cachedToken = null
    }
}
