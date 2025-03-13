package com.example.moviesapi.detail.presentation.model

data class DetailUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "Something went wrong",
    val listDetail: DetailUiData? = null
)

data class DetailUiData(
    val image: String,
    val overview: String,
    val original_title: String
)
