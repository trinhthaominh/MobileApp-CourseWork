package com.example.top_movie.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.top_movie.model.ApiResponse
import com.example.top_movie.model.RecommendationsModel
import com.example.top_movie.repository.APIRecommendRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class RecommendationsViewModel(private val repository: APIRecommendRepository) : ViewModel() {

    val responseValue : MutableLiveData<Response<ApiResponse>> = MutableLiveData()

    fun getRecommendations() {

        viewModelScope.launch {
            // Đảm bảo sử dụng viewModelScope.launch để chạy coroutine
            viewModelScope.launch {
                try {
                    // 1. Thực hiện gọi API
                    val response = repository.getRecommendations()

                    if (response.isSuccessful) {
                        responseValue.postValue(response)
                        Log.i("ViewModel", "API CALL SUCCESS: Data is ready.")
                    } else {
                        // 2. Log lỗi HTTP (VD: 404, 500)
                        val errorBody = response.errorBody()?.string() ?: "Unknown error"
                        Log.e("ViewModel", "API CALL FAILED: Code ${response.code()}, Error: $errorBody")
                        responseValue.postValue(response)
                    }

                } catch (e: Exception) {
                    // 3. Log các lỗi nghiêm trọng (VD: Lỗi mạng, Lỗi JSON Parsing)
                    Log.e("ViewModel", "CRITICAL EXCEPTION during API call!", e)
                }
            }

        }
    }



}
