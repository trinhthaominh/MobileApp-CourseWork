package com.example.top_movie.model

class RecommendationsModel(
    val audience_score : Int,
    val critics_score : Int,
    val ems_id : String?,
    val image_url : String,
    val media_type : String,
    val media_url: String,
    val title: String
) {
}