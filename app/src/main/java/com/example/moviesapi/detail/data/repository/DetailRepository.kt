package com.example.moviesapi.detail.data.repository

import com.example.moviesapi.detail.data.remote.dto.DetailDTO

interface DetailRepository {

    suspend fun fetchDetail(movieId: String): Result<DetailDTO>

}