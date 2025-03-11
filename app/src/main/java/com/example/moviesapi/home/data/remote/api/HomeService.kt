package com.example.moviesapi.home.data.remote.api

import com.example.moviesapi.commom.util.Constants.TOKEN
import com.example.moviesapi.home.data.remote.dto.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeService {

    @GET("top_rated")
    suspend fun getTopRated(@Header("Authorization") token: String = "Bearer $TOKEN"): Response<ApiResponse>

    @GET("popular")
    suspend fun getPopular(@Header("Authorization") token: String = "Bearer $TOKEN"): Response<ApiResponse>

    @GET("now_playing")
    suspend fun getNowPlaying(@Header("Authorization") token: String = "Bearer $TOKEN"): Response<ApiResponse>

    @GET("upcoming")
    suspend fun getUpcoming(@Header("Authorization") token: String = "Bearer $TOKEN"): Response<ApiResponse>

}