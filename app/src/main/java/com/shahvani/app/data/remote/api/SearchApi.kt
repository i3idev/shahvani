package com.shahvani.app.data.remote.api

import com.shahvani.app.data.remote.dto.SearchUsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    
    @GET("/api/v1/search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("limit") limit: Int? = null
    ): Response<SearchUsersResponse>
}
