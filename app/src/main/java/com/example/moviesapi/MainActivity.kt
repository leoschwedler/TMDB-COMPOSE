package com.example.moviesapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.moviesapi.data.remote.api.ApiService
import com.example.moviesapi.presentation.theme.MoviesAPITheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesAPITheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Gray
                ) { innerPadding ->
                    NavControllerApp(
                        apiService = apiService,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

