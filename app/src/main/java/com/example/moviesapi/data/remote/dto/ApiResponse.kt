package com.example.moviesapi.data.remote.dto

data class ApiResponse(
    val page: Int,
    val results: List<ResultDTO>,
)
