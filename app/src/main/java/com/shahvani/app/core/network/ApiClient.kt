package com.shahvani.app.core.network

import android.content.Context
import android.content.SharedPreferences
import com.shahvani.app.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    const val BASE_URL = "https://shahvani.com"

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        isLenient = true
        prettyPrint = BuildConfig.DEBUG
    }

    @Provides
    @Singleton
    fun providePersistentCookieJar(@ApplicationContext context: Context): CookieJar {
        return PersistentCookieJar(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        cookieJar: CookieJar,
        csrfTokenManager: CsrfTokenManager
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
                
                // Add standard headers and CSRF token for mutation requests
                addInterceptor { chain ->
                    val originalRequest = chain.request()
                    val requestBuilder = originalRequest.newBuilder()
                        .addHeader("Accept", "application/json")
                    
                    // Add CSRF token for POST, PUT, PATCH, DELETE requests
                    if (originalRequest.method in listOf("POST", "PUT", "PATCH", "DELETE")) {
                        val csrfToken = csrfTokenManager.getToken()
                        if (csrfToken != null) {
                            requestBuilder.addHeader("X-CSRF-Token", csrfToken)
                        }
                    }
                    
                    chain.proceed(requestBuilder.build())
                }
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
}

/**
 * Persistent CookieJar that stores cookies in SharedPreferences
 * This ensures session cookies survive app restarts
 */
private class PersistentCookieJar(private val context: Context) : CookieJar {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("cookies", Context.MODE_PRIVATE)
    
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val hostCookies = mutableMapOf<String, String>()
        
        // Store each cookie with its host
        for (cookie in cookies) {
            hostCookies[cookie.name] = cookie.toString()
        }
        
        if (hostCookies.isNotEmpty()) {
            prefs.edit().apply {
                putStringSet("cookies_${url.host}", hostCookies.keys)
                for ((name, value) in hostCookies) {
                    putString("cookie_${url.host}_$name", value)
                }
                apply()
            }
        }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val result = mutableListOf<Cookie>()
        val cookieNames = prefs.getStringSet("cookies_${url.host}", emptySet()) ?: emptySet()
        
        for (name in cookieNames) {
            val cookieString = prefs.getString("cookie_${url.host}_$name", null)
            if (cookieString != null) {
                val cookie = Cookie.parse(url, cookieString)
                if (cookie != null && !cookie.isExpired()) {
                    result.add(cookie)
                }
            }
        }
        
        return result
    }
    
    private fun Cookie.isExpired(): Boolean {
        return System.currentTimeMillis() > expiresAt
    }
}
