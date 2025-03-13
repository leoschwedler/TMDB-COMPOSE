package com.example.moviesapi.commom.model

import com.example.moviesapi.commom.data.local.entity.MovieEntity
import com.example.moviesapi.home.presentation.model.HomeUiData

data class Movie(
    val id: Int,
    val image: String,
    val overview: String,
    val title: String,
    val category: Category
)

fun Movie.toMovieEntity(): MovieEntity {
   return MovieEntity(
        id = id,
        image = image,
        overview = overview,
        title = title,
        category = category
    )
}

fun Movie.toHomeUiData(): HomeUiData {
    return HomeUiData(
        id = id,
        image = image,
        overview = overview,
        title = title
    )
}
