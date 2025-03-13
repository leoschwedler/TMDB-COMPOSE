package com.example.moviesapi.detail.data.repository

import android.accounts.NetworkErrorException
import com.example.moviesapi.detail.data.remote.api.DetailService
import com.example.moviesapi.detail.data.remote.dto.DetailDTO
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(val service: DetailService) : DetailRepository {
    override suspend fun fetchDetail(movieId: String): Result<DetailDTO> {
        return try {
            val response = service.getDetailMovie(movieId = movieId)
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) } ?: Result.failure(
                    IllegalStateException(
                        response.message()
                    )
                )
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}