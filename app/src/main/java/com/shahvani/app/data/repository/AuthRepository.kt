package com.shahvani.app.data.repository

import com.shahvani.app.core.network.CsrfTokenManager
import com.shahvani.app.data.local.dao.UserSessionDao
import com.shahvani.app.data.local.entity.UserSessionEntity
import com.shahvani.app.data.remote.api.AuthApi
import com.shahvani.app.data.remote.dto.AuthRequest
import com.shahvani.app.data.remote.dto.UserDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val userSessionDao: UserSessionDao,
    private val csrfTokenManager: CsrfTokenManager
) {
    val sessionFlow: Flow<UserSessionEntity?> = userSessionDao.getSessionFlow()
    
    suspend fun login(username: String, password: String): Result<UserDto> {
        return try {
            val csrfResult = csrfTokenManager.getOrFetchToken()
            if (!csrfResult.isSuccess) {
                return Result.failure(csrfResult.exceptionOrNull() ?: Exception("CSRF token fetch failed"))
            }
            val response = authApi.login(AuthRequest(username, password))
            if (response.isSuccessful && response.body()?.user != null) {
                val user = response.body()!!.user!!
                saveSession(user)
                Result.success(user)
            } else {
                Result.failure(Exception(response.body()?.error ?: "Login failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun register(username: String, password: String): Result<UserDto> {
        return try {
            val response = authApi.register(AuthRequest(username, password))
            if (response.isSuccessful && response.body()?.user != null) {
                val user = response.body()!!.user!!
                saveSession(user)
                Result.success(user)
            } else {
                Result.failure(Exception(response.body()?.error ?: "Registration failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getCurrentUser(): Result<UserDto> {
        return try {
            val response = authApi.getCurrentUser()
            if (response.isSuccessful && response.body() != null) {
                val user = response.body()!!
                saveSession(user)
                Result.success(user)
            } else {
                Result.failure(Exception("Not authenticated"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun logout(): Result<Unit> {
        return try {
            val response = authApi.logout()
            userSessionDao.clearSession()
            csrfTokenManager.clearToken()
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Logout failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private suspend fun saveSession(user: UserDto) {
        userSessionDao.saveSession(
            UserSessionEntity(
                username = user.username,
                email = user.email,
                avatar = user.avatar,
                isLoggedIn = true
            )
        )
    }
    
    suspend fun isLoggedIn(): Boolean {
        return userSessionDao.getSession()?.isLoggedIn == true
    }
}
