package com.example.moviesapi.presentation.model

import com.example.moviesapi.data.remote.dto.DetailDTO

data class UiStateDetail(
    val isLoading: Boolean = false,
    val error: String = "",
    val detailDTO: DetailDTO? = null
)
