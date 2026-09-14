package com.shahvani.app.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CsrfTokenManager @Inject constructor(
    private val okHttpClient: OkHttpClient
) {
    private var cachedToken: String? = null
    
    suspend fun fetchToken(): Result<String> = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url("${ApiClient.BASE_URL}/")
                .get()
                .build()
            
            val response = okHttpClient.newCall(request).execute()
            if (!response.isSuccessful) {
                return@withContext Result.failure(Exception("Failed to fetch CSRF token"))
            }
            
            val html = response.body?.string() ?: return@withContext Result.failure(Exception("Empty response"))
            val doc = Jsoup.parse(html)
            val token = doc.selectFirst("meta[name=csrf-token]")?.attr("content")
                ?: return@withContext Result.failure(Exception("CSRF token not found"))
            
            cachedToken = token
            Result.success(token)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    fun getToken(): String? = cachedToken
    
    fun clearToken() {
        cachedToken = null
    }
}
