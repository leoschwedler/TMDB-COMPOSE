package com.example.moviesapi.home.data.repository

import com.example.moviesapi.commom.model.Result
import com.example.moviesapi.home.data.remote.api.HomeService
import com.example.moviesapi.home.data.remote.dto.ResultDTO
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(val service: HomeService): HomeRepository {

    override suspend fun fetchUpcoming(): Result<List<ResultDTO>> {
        return try {
            val response = service.getUpcoming()
            if (response.isSuccessful) {
                response.body()?.results?.let {
                    Result.Success(it)
                } ?: Result.Error("Resposta vazia")
            } else {
                Result.Error("Error code: ${response.code()} - Error body: ${response.errorBody()?.toString()}")
            }
        } catch (e: Exception) {
            Result.Error("Exception: $e")
        }
    }

    override suspend fun fetchPopular(): Result<List<ResultDTO>> {
        return try {
            val response = service.getPopular()
            if (response.isSuccessful){
                response.body()?.results?.let { Result.Success(it) } ?: Result.Error("Response void")
            }else{
                Result.Error("Error code: ${response.code()} - Error body: ${response.errorBody()?.toString()}")
            }
        }catch (e: Exception){
            Result.Error("Exception $e")
        }
    }

    override suspend fun fetchTopRated(): Result<List<ResultDTO>> {
        return try {
            val response = service.getTopRated()
            if (response.isSuccessful){
                response.body()?.results?.let { Result.Success(it) } ?: Result.Error("Response void")
            }else{
                Result.Error("Error code: ${response.code()} - Error body: ${response.errorBody()?.toString()}")
            }
        }catch (e: Exception){
            Result.Error("Exception: $e")
        }
    }

    override suspend fun fetchNowPlaying(): Result<List<ResultDTO>> {
        return try {
            val response = service.getNowPlaying()
            if (response.isSuccessful){
                response.body()?.results?.let { Result.Success(it) } ?: Result.Error("Response void")
            }else{
                Result.Error("Error code: ${response.code()} - Error body: ${response.errorBody()?.toString()}")
            }
        }catch (e: Exception){
            Result.Error("Exception: $e")
        }
    }
}