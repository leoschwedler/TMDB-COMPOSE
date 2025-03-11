package com.example.moviesapi.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapi.commom.model.Result
import com.example.moviesapi.home.data.remote.dto.toHomeUiData
import com.example.moviesapi.home.data.repository.HomeRepository
import com.example.moviesapi.home.presentation.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadUpcoming()
        loadPopular()
        loadTopRated()
        loadNowPlaying()
    }

    fun loadUpcoming() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.fetchUpcoming()
            when (result) {
                is Result.Success -> {
                    val homeUiData = result.data.map { it.toHomeUiData() }
                    _uiState.update { it.copy(upcoming = homeUiData, isLoading = false) }
                }

                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isError = true,
                            isLoading = false,
                            errorMessage = result.errorMessage
                        )
                    }
                }
            }
        }
    }

    fun loadPopular() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.fetchPopular()
            when (result) {
                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isError = true,
                            errorMessage = result.errorMessage,
                            isLoading = false
                        )
                    }
                }

                is Result.Success -> {
                    val homeUiData = result.data.map { it.toHomeUiData() }
                    _uiState.update { it.copy(popular = homeUiData, isLoading = false) }
                }
            }
        }
    }

    fun loadTopRated() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.fetchTopRated()
            when (result) {
                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = result.errorMessage
                        )
                    }
                }

                is Result.Success -> {
                    val homeUiData = result.data.map { it.toHomeUiData() }
                    _uiState.update { it.copy(topRated = homeUiData, isLoading = false) }
                }
            }
        }
    }

    fun loadNowPlaying() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.fetchNowPlaying()
            when (result) {
                is Result.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = result.errorMessage
                        )
                    }
                }

                is Result.Success -> {
                    val homeUiData = result.data.map { it.toHomeUiData() }
                    _uiState.update { it.copy(isLoading = false, nowPlaying = homeUiData) }
                }
            }
        }
    }

}