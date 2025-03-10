package com.example.moviesapi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapi.data.remote.api.ApiService
import com.example.moviesapi.presentation.model.UiStateHome
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val service: ApiService) : ViewModel() {

    private val _uiState = MutableStateFlow(UiStateHome())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetUpcoming()
            fetPopular()
            fetTopRated()
            fetNowPlaying()
        }
    }

    suspend fun fetUpcoming() {
        try {
            _uiState.update {
                it.copy(isLoading = true)
            }
            val response = service.getUpcoming()
            if (response.isSuccessful) {
                response.body()?.results?.let { upcoming ->
                    _uiState.update {
                        it.copy(
                            upcoming = upcoming,
                            isLoading = false
                        )
                    }
                }
            } else {
                Log.e(
                    "MainActivity",
                    "Error code - ${response.code()} - Error Body ${response.errorBody()}"
                )
                _uiState.update {
                    it.copy(error = "Error code - ${response.code()} - Error Body ${response.errorBody()}\"")
                }
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Exception $e")
            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = "Exception $e"
                )
            }
        }
    }

    suspend fun fetPopular() {
        try {
            _uiState.update { it.copy(isLoading = true) }
            val response = service.getPopular()
            if (response.isSuccessful) {
                response.body()?.results?.let { popular ->
                    _uiState.update {
                        it.copy(
                            popular = popular
                        )
                    }
                }
            } else {
                Log.e(
                    "MainActivity",
                    "Error code ${response.code()} - Error Body ${response.errorBody()}"
                )
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Error code ${response.code()} - Error Body ${response.errorBody()}"
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Exceção ao buscar filmes $e")
            _uiState.update { it.copy(isLoading = false, error = "Exceção ao buscar filmes, $e") }
        }
    }

    suspend fun fetTopRated() {
        try {
            _uiState.update { it.copy(isLoading = true) }
            val response = service.getTopRated()
            if (response.isSuccessful) {
                response.body()?.results?.let { topRated ->
                    _uiState.update {
                        it.copy(
                            topRated = topRated,
                            isLoading = false
                        )
                    }
                }
            } else {
                Log.e("MainActivity", "Erro ${response.code()} - ${response.errorBody()}")
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Erro ${response.code()} - ${response.errorBody()}"
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Exceção ao buscar filmes $e")
            _uiState.update { it.copy(isLoading = false, error = "Exceção ao buscar filmes $e") }
        }
    }

    suspend fun fetNowPlaying() {
        try {
            _uiState.update { it.copy(isLoading = true) }
            val response = service.getNowPlaying()
            if (response.isSuccessful) {
                response.body()?.results?.let { nowPlaying ->
                    _uiState.update { it.copy(isLoading = false, nowPlaying = nowPlaying) }
                }
            } else {
                Log.e(
                    "MainActivity",
                    "Error code ${response.code()} - Error Body ${response.errorBody()}"
                )
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = "Error code ${response.code()} - Error Body ${response.errorBody()}"
                    )
                }
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Exception : $e")
            _uiState.update { it.copy(isLoading = false, error = "Exception : $e") }
        }
    }
}