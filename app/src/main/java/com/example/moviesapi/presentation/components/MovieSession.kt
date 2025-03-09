package com.example.moviesapi.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.moviesapi.data.remote.dto.ResultDTO

@Composable
fun MovieSession(
    label: String,
    movie: List<ResultDTO>,
    onclick: (ResultDTO) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
        MovieList(movie = movie, onclick = onclick)
    }
}