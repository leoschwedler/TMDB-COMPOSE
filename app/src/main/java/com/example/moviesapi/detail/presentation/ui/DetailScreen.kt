package com.example.moviesapi.detail.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.moviesapi.detail.presentation.model.DetailUiState
import com.example.moviesapi.detail.presentation.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    itemId: String,
    navHostController: NavHostController,
    viewModel: DetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(itemId) {
        viewModel.loadDetail(movieId = itemId)
    }

    DetailContent(
        uiState = uiState,
        onBack = {
            navHostController.popBackStack()
        },
        modifier = modifier
    )


}

@Composable
fun DetailContent(
    uiState: DetailUiState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }

            uiState.isError -> {
                Text(text = uiState.errorMessage, color = Color.Red)
            }

            else -> {
                AsyncImage(
                    model = uiState.listDetail?.image,
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                uiState.listDetail?.original_title?.let { Text(it) }
            }
        }


        Button(onClick = onBack) {
            Text("BACK")

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailPreview() {

}