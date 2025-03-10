package com.example.moviesapi.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.moviesapi.data.remote.api.ApiService
import com.example.moviesapi.data.remote.dto.DetailDTO
import com.example.moviesapi.data.remote.dto.ResultDTO
import com.example.moviesapi.presentation.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    itemId: String,
    navHostController: NavHostController,
    viewModel: DetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchDetail(itemId)
    }

    uiState.detailDTO?.let {
        DetailContent(
            modifier = modifier,
            resultDto = it,
            onBack = {
                navHostController.popBackStack()
            }
        )
    }
}

@Composable
fun DetailContent(
    resultDto: DetailDTO,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = resultDto.fullPatch,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(resultDto.original_title)


        Button(onClick = onBack) {
            Text("BACK TU BACK")

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailPreview() {

}