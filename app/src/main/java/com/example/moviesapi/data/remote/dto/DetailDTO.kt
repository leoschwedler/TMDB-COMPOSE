package com.example.moviesapi.data.remote.dto

data class DetailDTO(
    val backdrop_path: String,
    val overview: String,
    val original_title: String
){
    val fullPatch get() = "https://image.tmdb.org/t/p/w300$backdrop_path"
}
