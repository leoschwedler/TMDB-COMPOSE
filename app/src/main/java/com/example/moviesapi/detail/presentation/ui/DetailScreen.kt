package com.example.moviesapi.detail.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.moviesapi.detail.data.remote.dto.DetailDTO
import com.example.moviesapi.detail.presentation.model.DetailUiData
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
        viewModel.loadDetail(id = itemId)
    }


    uiState.detailUiData?.let {
        DetailContent(
            modifier = modifier,
            detailUIData = it,
            onBack = {
                navHostController.popBackStack()
            }
        )
    }

}

@Composable
fun DetailContent(
    detailUIData: DetailUiData,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = detailUIData.image,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Text(detailUIData.original_title)


        Button(onClick = onBack) {
            Text("BACK TU BACK")

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailPreview() {

}