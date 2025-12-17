package com.example.top_movie.view

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.top_movie.databinding.ActivityMainBinding
import com.example.top_movie.repository.APIRecommendRepository
import com.example.top_movie.viewmodel.RecommendationsViewModel
import com.example.top_movie.viewmodel.RecommendationsViewModelFactory



class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: RecommendationsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModelFactory = RecommendationsViewModelFactory(APIRecommendRepository())
        viewModel = ViewModelProvider(this, viewModelFactory)[RecommendationsViewModel::class.java]
        viewModel.getRecommendations()
        viewModel.responseValue.observe(this) { response ->
            if (response.isSuccessful) {
                val recommendationList = response.body()?.recommendations
                var lvRecommend = binding.lvRecommend
                var listRecommend = mutableListOf<String>()
                for (item in recommendationList!!) {
                    listRecommend.add(item.title)
                }
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    listRecommend
                )

                lvRecommend.adapter = adapter
            } else {
                // Xử lý lỗi
            }
        }










    }
}