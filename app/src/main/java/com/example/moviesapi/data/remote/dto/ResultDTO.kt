package com.example.moviesapi.data.remote.dto

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
