package com.example.top_movie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.top_movie.repository.APIRecommendRepository

class RecommendationsViewModelFactory (private val repository: APIRecommendRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecommendationsViewModel(repository) as T
    }
}
