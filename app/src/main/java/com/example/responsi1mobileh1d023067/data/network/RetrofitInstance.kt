package com.example.responsi1mobileh1d023067.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.responsi1mobileh1d023067.utils.Constants

object RetrofitInstance {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-Auth-Token", Constants.API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()


    val api: FootballApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FootballApi::class.java)
    }
}