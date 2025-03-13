package com.example.moviesapi.home.presentation.model

import com.example.moviesapi.home.data.remote.dto.ResultDTO
import com.google.gson.annotations.SerializedName

data class HomeUiState(
    val nowPlaying: List<HomeUiData> = emptyList(),
    val popular: List<HomeUiData> = emptyList(),
    val topRated: List<HomeUiData> = emptyList(),
    val upcoming: List<HomeUiData> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "Something went wrong"
)

data class HomeUiData(
    val id: Int,
    val image: String,
    val overview: String,
    val title: String
)
