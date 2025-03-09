package com.example.moviesapi.presentation.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviesapi.data.remote.dto.ResultDTO


@Composable
fun MovieList(
    movie: List<ResultDTO>,
    onclick: (ResultDTO) -> Unit,
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