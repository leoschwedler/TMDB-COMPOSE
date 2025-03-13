package com.example.moviesapi.commom.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesapi.commom.data.local.dao.MovieDao
import com.example.moviesapi.commom.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}