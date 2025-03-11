package com.example.moviesapi.home.data.remote.dto

data class ApiResponse(
    val page: Int,
    val results: List<ResultDTO>,
)
