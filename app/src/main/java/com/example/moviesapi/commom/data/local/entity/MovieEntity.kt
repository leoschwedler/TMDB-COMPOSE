package com.example.moviesapi.commom.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesapi.commom.model.Category
import com.example.moviesapi.commom.model.Movie

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "poster_path")
    val image: String,
    val overview: String,
    val title: String,
    val category: Category
)

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        image = image,
        overview = overview,
        title = title,
        category = category
    )
}

