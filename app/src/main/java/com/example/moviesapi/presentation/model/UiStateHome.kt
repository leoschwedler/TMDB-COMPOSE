package com.example.moviesapi.presentation.model

import com.example.moviesapi.data.remote.dto.ResultDTO

data class UiStateHome(
    val nowPlaying: List<ResultDTO> = emptyList(),
    val popular: List<ResultDTO> = emptyList(),
    val topRated: List<ResultDTO> = emptyList(),
    val upcoming: List<ResultDTO> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
