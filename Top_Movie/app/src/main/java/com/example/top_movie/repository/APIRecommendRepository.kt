package com.example.top_movie.repository

import com.example.top_movie.model.ApiResponse
import com.example.top_movie.model.RecommendationsModel
import com.example.top_movie.model.RetrofitInstance
import retrofit2.Response

class APIRecommendRepository {
    suspend fun getRecommendations(): Response<ApiResponse> {
        return RetrofitInstance.apiJsonPlaceholder.getRecommendations()
    }
}