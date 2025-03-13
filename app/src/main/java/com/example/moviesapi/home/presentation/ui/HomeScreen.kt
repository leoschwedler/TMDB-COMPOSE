package com.example.moviesapi.home.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.moviesapi.home.data.remote.dto.ResultDTO
import com.example.moviesapi.commom.components.MovieSession
import com.example.moviesapi.home.presentation.model.HomeUiData
import com.example.moviesapi.home.presentation.model.HomeUiState
import com.example.moviesapi.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()


    HomeContent(
        homeUiState = uiState,
        onClick = { resultDto ->
            navController.navigate(route = "DetailScreen/${resultDto.id}")
        },
        modifier = modifier
    )

}

@Composable
private fun HomeContent(
    homeUiState: HomeUiState,
    onClick: (HomeUiData) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Text("CineNow", fontWeight = FontWeight.SemiBold, fontSize = 40.sp)
        Spacer(Modifier.height(20.dp))
        MovieSession(
            label = "Top Rated", onclick = onClick, movie = homeUiState.topRated, uiState = homeUiState
        )
        MovieSession(
            label = "Now Playing", onclick = onClick, movie = homeUiState.nowPlaying, uiState = homeUiState
        )
        MovieSession(
            label = "Popular", onclick = onClick, movie = homeUiState.popular, uiState = homeUiState,
        )
        MovieSession(
            label = "Upcoming", onclick = onClick, movie = homeUiState.upcoming, uiState = homeUiState,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun HomePreview() {

}