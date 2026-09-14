package com.shahvani.app.data.remote.api

import com.shahvani.app.data.remote.dto.GalleryDto
import com.shahvani.app.data.remote.dto.GalleryItemDto
import com.shahvani.app.data.remote.dto.ProfileContentDto
import com.shahvani.app.data.remote.dto.ProfileDto
import com.shahvani.app.data.remote.dto.ProfileStatsDto
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProfileApi {
    
    @GET("/api/v1/profiles/{username}")
    suspend fun getProfile(@Path("username") username: String): Response<ProfileDto>
    
    @GET("/api/v1/profiles/{username}/stats")
    suspend fun getProfileStats(@Path("username") username: String): Response<ProfileStatsDto>
    
    @GET("/api/v1/profiles/{username}/{type}")
    suspend fun getProfileContent(
        @Path("username") username: String,
        @Path("type") type: String,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<ProfileContentDto>
    
    @GET("/api/v1/profiles/{username}/gallery")
    suspend fun getGallery(
        @Path("username") username: String,
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null
    ): Response<GalleryDto>
    
    @POST("/api/v1/users/{userId}/block")
    suspend fun blockUser(@Path("userId") userId: Int): Response<Unit>
    
    @POST("/api/v1/users/{userId}/unblock")
    suspend fun unblockUser(@Path("userId") userId: Int): Response<Unit>
    
    @DELETE("/api/v1/users/profile/gallery/{mediaId}")
    suspend fun deleteGalleryMedia(@Path("mediaId") mediaId: Int): Response<Unit>
}
