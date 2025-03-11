package com.example.moviesapi.detail.data.remote.api

import com.example.moviesapi.commom.util.Constants.TOKEN
import com.example.moviesapi.detail.data.remote.dto.DetailDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface DetailService {

    @GET("{movie_id}")
    suspend fun getDetailMovie(
        @Header("Authorization") token: String = "Bearer $TOKEN",
        @Path("movie_id") movieId: String
    ): Response<DetailDTO>

}