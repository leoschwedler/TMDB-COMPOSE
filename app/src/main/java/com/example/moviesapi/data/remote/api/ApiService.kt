package com.example.moviesapi.data.remote.api


import com.example.moviesapi.data.remote.dto.ApiResponse
import com.example.moviesapi.data.remote.dto.DetailDTO
import com.example.moviesapi.commom.Constants.TOKEN
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    @GET("top_rated")
    suspend fun getTopRated(@Header("Authorization") token: String = "Bearer $TOKEN"): Response<ApiResponse>

    @GET("popular")
    suspend fun getPopular(@Header("Authorization") token: String = "Bearer $TOKEN"): Response<ApiResponse>

    @GET("now_playing")
    suspend fun getNowPlaying(@Header("Authorization") token: String = "Bearer $TOKEN"): Response<ApiResponse>

    @GET("upcoming")
    suspend fun getUpcoming(@Header("Authorization") token: String = "Bearer $TOKEN"): Response<ApiResponse>

    @GET("{movie_id}")
    suspend fun getDetailMovie(
        @Header("Authorization") token: String = "Bearer $TOKEN",
        @Path("movie_id") movieId: String
    ): Response<DetailDTO>

}