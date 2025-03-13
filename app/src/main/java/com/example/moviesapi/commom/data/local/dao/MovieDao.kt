package com.example.moviesapi.commom.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.moviesapi.commom.data.local.entity.MovieEntity
import com.example.moviesapi.commom.model.Category

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table WHERE category IN (:category)")
    suspend fun getByCategory(category: Category): List<MovieEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieList: List<MovieEntity>)

}