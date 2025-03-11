package com.example.moviesapi.detail.data.remote.dto

import com.example.moviesapi.detail.presentation.model.DetailUiData

data class DetailDTO(
    val backdrop_path: String,
    val overview: String,
    val original_title: String
){
    val fullPatch get() = "https://image.tmdb.org/t/p/w300$backdrop_path"
}

fun DetailDTO.toDetailUiData(): DetailUiData{
    return DetailUiData(
        image = fullPatch,
        overview = overview,
        original_title = original_title
    )
}
