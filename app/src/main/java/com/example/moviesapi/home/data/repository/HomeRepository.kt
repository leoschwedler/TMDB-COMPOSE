package com.example.moviesapi.home.data.repository

import com.example.moviesapi.home.data.remote.dto.ResultDTO
import com.example.moviesapi.commom.model.Result


interface HomeRepository {

    suspend fun fetchUpcoming(): Result<List<ResultDTO>>

    suspend fun fetchPopular(): Result<List<ResultDTO>>

    suspend fun fetchTopRated(): Result<List<ResultDTO>>

    suspend fun fetchNowPlaying(): Result<List<ResultDTO>>
}