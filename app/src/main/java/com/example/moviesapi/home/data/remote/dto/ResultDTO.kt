package com.example.moviesapi.home.data.remote.dto

import com.example.moviesapi.home.presentation.model.HomeUiData
import com.google.gson.annotations.SerializedName

data class ResultDTO(
    val id: Int,
    @SerializedName(value = "poster_path")
    val posterPath: String,
    val overview: String,
    val title: String
){
    val fullPatch get() = "https://image.tmdb.org/t/p/w300$posterPath"
}

fun ResultDTO.toHomeUiData(): HomeUiData{
    return HomeUiData(
        id = id,
        image =  fullPatch,
        overview = overview,
        title = title
    )
}
