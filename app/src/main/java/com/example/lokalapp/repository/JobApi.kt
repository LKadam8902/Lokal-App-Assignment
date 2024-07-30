package com.example.lokalapp.repository

import com.example.lokalapp.pojo.Jobs
import com.example.lokalapp.pojo.Result
import retrofit2.Call
import retrofit2.http.GET

interface JobApi {

    @GET("jobs?page=1")
    fun getJobsDetail(): Call<Jobs>


}