package com.shahvani.app.data.remote.api

import com.shahvani.app.data.remote.dto.MediaResponse
import com.shahvani.app.data.remote.dto.UploadResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface UploadApi {
    
    @Multipart
    @POST("/api/v1/upload")
    suspend fun uploadFile(@Part file: MultipartBody.Part): Response<UploadResponse>
    
    @Multipart
    @POST("/api/v1/users/profile/gallery")
    suspend fun uploadToGallery(@Part media: MultipartBody.Part): Response<MediaResponse>
    
    @DELETE("/api/v1/users/profile/gallery/{mediaId}")
    suspend fun deleteFromGallery(@Path("mediaId") mediaId: Int): Response<Unit>
}
