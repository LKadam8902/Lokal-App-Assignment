package com.example.lokalapp.repository


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Create an OkHttpClient and add the logging interceptor
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://testapi.getlokalapp.com/common/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val jobApi: JobApi by lazy {
        instance.create(JobApi::class.java)
    }
}