package com.example.moviesapi.commom.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesapi.home.presentation.model.HomeUiData
import com.example.moviesapi.home.presentation.model.HomeUiState

@Composable
fun MovieSession(
    label: String,
    uiState: HomeUiState,
    movie: List<HomeUiData>,
    onclick: (HomeUiData) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxWidth()) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(64.dp))
            }

            uiState.isError -> {
                Text(
                    text = uiState.errorMessage,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Red
                )
            }

            else -> {
                MovieList(movie = movie, onclick = onclick)
            }
        }
    }
}