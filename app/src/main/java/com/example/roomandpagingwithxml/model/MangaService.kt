package com.example.roomandpagingwithxml.model

import com.example.roomandpagingwithxml.data.MangaResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface MangaService {
    @GET("manga")
    suspend fun getAllMangas(
        @Query("page") page: Int
    ): MangaResponse
}