package com.example.moviesapi.commom.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.moviesapi.home.data.remote.dto.ResultDTO
import com.example.moviesapi.home.presentation.model.HomeUiData

@Composable
fun MovieSession(
    label: String,
    movie: List<HomeUiData>,
    onclick: (HomeUiData) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
        MovieList(movie = movie, onclick = onclick)
    }
}