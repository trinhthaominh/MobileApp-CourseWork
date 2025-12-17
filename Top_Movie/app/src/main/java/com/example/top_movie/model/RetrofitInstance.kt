package com.example.top_movie.model
import com.example.top_movie.CmmVariable.Companion.API_KEY
import kotlin.getValue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.top_movie.CmmVariable.Companion.BASE_URL
import okhttp3.OkHttpClient

object RetrofitInstance {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()

            val newRequest = originalRequest.newBuilder()
                // Giả sử API của bạn sử dụng header "Authorization" với Bearer token.
                // Nếu API của bạn dùng cách khác (vd: "x-api-key"), hãy thay đổi tương ứng.
                .header("x-rapidapi-key", API_KEY)
                .build()

            // Tiếp tục chuỗi request với request đã được sửa đổi
            chain.proceed(newRequest)
        }
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // Đặt OkHttpClient đã cấu hình vào Retrofit
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }
    val apiJsonPlaceholder: APIJSONPlaceholder by lazy {
        retrofit.create(APIJSONPlaceholder::class.java)
    }
}
