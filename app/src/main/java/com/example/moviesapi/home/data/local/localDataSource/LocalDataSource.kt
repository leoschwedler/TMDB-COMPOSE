package com.example.moviesapi.home.data.local.localDataSource

import com.example.moviesapi.commom.data.local.dao.MovieDao
import com.example.moviesapi.commom.data.local.entity.toMovie
import com.example.moviesapi.commom.model.Category
import com.example.moviesapi.commom.model.Movie
import com.example.moviesapi.commom.model.toMovieEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(val movieDao: MovieDao) {

    suspend fun getUpcoming(): List<Movie> {
        val movieEntity = movieDao.getByCategory(Category.UPCOMING)
        return movieEntity.map { it.toMovie() }

    }

    suspend fun getPopular(): List<Movie>  {
        val movieEntity = movieDao.getByCategory(Category.POPULAR)
        return movieEntity.map { it.toMovie() }
    }

    suspend fun getTopRated(): List<Movie>  {
        val movieEntity = movieDao.getByCategory(Category.TOP_RATED)
        return movieEntity.map { it.toMovie() }
    }


    suspend fun getNowPlaying(): List<Movie>  {
        val movieEntity = movieDao.getByCategory(Category.NOW_PLAYING)
        return movieEntity.map { it.toMovie() }
    }

    suspend fun updateLocalDataBase(movie: List<Movie>){
        val entity = movie.map { it.toMovieEntity() }
        movieDao.insertAll(entity)
    }

}