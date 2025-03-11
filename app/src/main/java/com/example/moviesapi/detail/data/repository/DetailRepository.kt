package com.example.moviesapi.detail.data.repository

import com.example.moviesapi.commom.model.Result
import com.example.moviesapi.detail.data.remote.dto.DetailDTO

interface DetailRepository {

    suspend fun fetchDetail(id: String): Result<DetailDTO>

}