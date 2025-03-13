package com.example.moviesapi.home.data.repository

import com.example.moviesapi.commom.model.Movie
import com.example.moviesapi.home.data.local.localDataSource.LocalDataSource
import com.example.moviesapi.home.data.remote.RemoteDataSource.RemoteDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    val local: LocalDataSource,
    val remote: RemoteDataSource
) : HomeRepository {

    companion object {
        private const val TAG = "HomeRepositoryImpl"
    }

    override suspend fun fetchUpcoming(): Result<List<Movie>> {
        return try {
            val result = remote.fetchUpcoming()
            if (result.isSuccess){
                val moviesRemote = result.getOrNull() ?: emptyList()
                if (moviesRemote.isNotEmpty()){
                    local.updateLocalDataBase(moviesRemote)
                }
            }else{
                val localData = local.getUpcoming()
                if (localData.isEmpty()){
                    return result
                }
            }
            Result.success(local.getUpcoming())
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun fetchPopular(): Result<List<Movie>> {
        return try {
            val result = remote.fetchPopular()
            if (result.isSuccess){
                val moviesRemote = result.getOrNull() ?: emptyList()
                if (moviesRemote.isNotEmpty()){
                    local.updateLocalDataBase(moviesRemote)
                }
            }else{
                val localData = local.getPopular()
                if (localData.isEmpty()){
                    return result
                }
            }
            Result.success(local.getPopular())
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun fetchTopRated(): Result<List<Movie>> {
        return try {
            val result = remote.fetchTopRated()
            if (result.isSuccess){
                val moviesRemote = result.getOrNull() ?: emptyList()
                if (moviesRemote.isNotEmpty()){
                    local.updateLocalDataBase(moviesRemote)
                }
            }else{
                val localData = local.getTopRated()
                if (localData.isEmpty()){
                    return result
                }
            }
            Result.success(local.getTopRated())
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun fetchNowPlaying(): Result<List<Movie>> {
        return try {
            val result = remote.fetchNowPlaying()
            if (result.isSuccess){
                val moviesRemote = result.getOrNull() ?: emptyList()
                if (moviesRemote.isNotEmpty()){
                    local.updateLocalDataBase(moviesRemote)
                }
            }else{
                val localData = local.getNowPlaying()
                if (localData.isEmpty()){
                    return result
                }
            }
            Result.success(local.getNowPlaying())
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}