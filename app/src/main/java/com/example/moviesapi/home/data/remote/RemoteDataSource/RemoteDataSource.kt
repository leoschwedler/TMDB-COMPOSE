package com.example.moviesapi.home.data.remote.RemoteDataSource

import android.accounts.NetworkErrorException
import com.example.moviesapi.commom.model.Category
import com.example.moviesapi.commom.model.Movie
import com.example.moviesapi.home.data.remote.api.HomeService
import com.example.moviesapi.home.data.remote.dto.toMovie
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: HomeService) {

    suspend fun fetchUpcoming(): Result<List<Movie>> {
        return try {
            val response = service.getUpcoming()
            if (response.isSuccessful) {
                response.body()?.results?.map { it.toMovie(Category.UPCOMING) }
                    ?.let { Result.success(it) } ?: Result.failure(
                    IllegalStateException(response.message())
                )
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchPopular(): Result<List<Movie>> {
        return try {
            val response = service.getPopular()
            if (response.isSuccessful) {
                response.body()?.results?.map { it.toMovie(Category.POPULAR) }
                    ?.let { Result.success(it) } ?: Result.failure(
                    IllegalStateException(response.message())
                )
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchTopRated(): Result<List<Movie>> {
        return try {
            val response = service.getTopRated()
            if (response.isSuccessful) {
                response.body()?.results?.map { it.toMovie(Category.TOP_RATED) }
                    ?.let { Result.success(it) } ?: Result.failure(
                    IllegalStateException(response.message())
                )
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchNowPlaying(): Result<List<Movie>> {
        return try {
            val response = service.getNowPlaying()
            if (response.isSuccessful) {
                response.body()?.results?.map { it.toMovie(Category.NOW_PLAYING) }
                    ?.let { Result.success(it) } ?: Result.failure(
                    IllegalStateException(response.message())
                )
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}