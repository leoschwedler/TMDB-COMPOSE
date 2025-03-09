package com.example.moviesapi.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moviesapi.data.remote.api.ApiService
import com.example.moviesapi.data.remote.dto.ResultDTO
import com.example.moviesapi.presentation.components.MovieSession

@Composable
fun HomeScreen(
    apiService: ApiService,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var nowPlaying by remember { mutableStateOf<List<ResultDTO>>(emptyList()) }
    var popular by remember { mutableStateOf<List<ResultDTO>>(emptyList()) }
    var topRated by remember { mutableStateOf<List<ResultDTO>>(emptyList()) }
    var upcoming by remember { mutableStateOf<List<ResultDTO>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val response = apiService.getUpcoming()
            if (response.isSuccessful) {
                response.body()?.results?.let {
                    upcoming = it
                }
            } else {
                Log.e(
                    "MainActivity",
                    "Error code - ${response.code()} - Error Body ${response.errorBody()}"
                )
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Exception $e")
        }
    }

    LaunchedEffect(Unit) {
        try {
            val response = apiService.getPopular()
            if (response.isSuccessful) {
                response.body()?.results?.let {
                    popular = it
                }
            } else {
                Log.e(
                    "MainActivity",
                    "Error code ${response.code()} - Error Body ${response.errorBody()}"
                )
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Exceção ao buscar filmes", e)
        }
    }

    LaunchedEffect(Unit) {
        try {
            val response = apiService.getTopRated()
            if (response.isSuccessful) {
                response.body()?.results?.let {
                    topRated = it
                }
            } else {
                Log.e("MainActivity", "Erro ${response.code()} - ${response.errorBody()}")
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Exceção ao buscar filmes", e)
        }
    }

    LaunchedEffect(Unit) {
        try {
            val response = apiService.getNowPlaying()
            if (response.isSuccessful) {
                response.body()?.results?.let {
                    nowPlaying = it
                }
            } else {
                Log.e(
                    "MainActivity",
                    "Error code ${response.code()} - Error Body ${response.errorBody()}"
                )
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Exception : $e")
        }
    }


    HomeContent(
        topRated = topRated,
        nowPlaying = nowPlaying,
        popular = popular,
        upcoming = upcoming,
        onClick = { resultDto ->
            navController.navigate(route = "DetailScreen/${resultDto.id}")
        },
        modifier = modifier
    )

}

@Composable
private fun HomeContent(
    topRated: List<ResultDTO>,
    nowPlaying: List<ResultDTO>,
    popular: List<ResultDTO>,
    upcoming: List<ResultDTO>,
    onClick: (ResultDTO) -> Unit,
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
        Text("CineLelis", fontWeight = FontWeight.SemiBold, fontSize = 40.sp)
        Spacer(Modifier.height(20.dp))
        MovieSession(
            label = "Top Rated", onclick = onClick, movie = topRated
        )
        MovieSession(
            label = "Now Playing", onclick = onClick, movie = nowPlaying
        )
        MovieSession(
            label = "Popular", onclick = onClick, movie = popular
        )
        MovieSession(
            label = "Upcoming", onclick = onClick, movie = upcoming
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun HomePreview() {

}