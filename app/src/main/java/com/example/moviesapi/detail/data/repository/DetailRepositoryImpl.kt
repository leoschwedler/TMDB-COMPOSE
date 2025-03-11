package com.example.moviesapi.detail.data.repository

import com.example.moviesapi.commom.model.Result
import com.example.moviesapi.detail.data.remote.api.DetailService
import com.example.moviesapi.detail.data.remote.dto.DetailDTO
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(val service: DetailService): DetailRepository {

    override suspend fun fetchDetail(id: String): Result<DetailDTO> {
       return try {
           val response = service.getDetailMovie(movieId = id)
           if (response.isSuccessful){
               response.body()?.let { Result.Success(it) } ?: Result.Error("Response void")
           }else{
               Result.Error("Error code: ${response.code()} - Error body: ${response.errorBody()?.toString()}")
           }
       }catch (e: Exception){
           Result.Error("Exception: $e")
       }
    }

}