package com.example.top_movie.model

import android.content.pm.ApplicationInfo
import retrofit2.Response
import retrofit2.http.GET

interface APIJSONPlaceholder {
    @GET("/today-recomendations")
    suspend fun getRecommendations() : Response<ApiResponse>
}