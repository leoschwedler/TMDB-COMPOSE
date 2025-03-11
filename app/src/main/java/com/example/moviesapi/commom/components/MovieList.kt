package com.example.moviesapi.commom.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviesapi.home.data.remote.dto.ResultDTO
import com.example.moviesapi.home.presentation.model.HomeUiData


@Composable
fun MovieList(
    movie: List<HomeUiData>,
    onclick: (HomeUiData) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        items(movie) { resultDto ->
            MovieItem(movie = resultDto, onclick = onclick)
        }
    }
}