package com.example.moviesapi.home.data.repository

import com.example.moviesapi.commom.model.Movie


interface HomeRepository {

    suspend fun fetchUpcoming(): Result<List<Movie>>

    suspend fun fetchPopular(): Result<List<Movie>>

    suspend fun fetchTopRated(): Result<List<Movie>>

    suspend fun fetchNowPlaying(): Result<List<Movie>>
}