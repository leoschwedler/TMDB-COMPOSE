package com.example.moviesapi.detail.presentation.model

import com.example.moviesapi.detail.data.remote.dto.DetailDTO

data class DetailUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val detailUiData: DetailUiData? = null
)

data class DetailUiData(
    val image: String,
    val overview: String,
    val original_title: String
)
