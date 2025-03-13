package com.example.moviesapi.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapi.commom.model.toHomeUiData
import com.example.moviesapi.home.data.repository.HomeRepository
import com.example.moviesapi.home.presentation.model.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException
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

    fun loadNowPlaying() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.fetchNowPlaying().fold(
                onSuccess = {
                    val result = it.map { it.toHomeUiData() }
                    _uiState.update { it.copy(nowPlaying = result, isLoading = false) }
                },
                onFailure = {
                    if (it is UnknownHostException) {
                        _uiState.update {
                            it.copy(
                                isError = true,
                                errorMessage = "Not internet connection",
                                isLoading = false
                            )
                        }
                    } else {
                        _uiState.update { it.copy(isError = true, isLoading = false) }
                    }
                }
            )
        }
    }

    fun loadTopRated() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.fetchTopRated()
            result.fold(
                onSuccess = {
                    val result = it.map { it.toHomeUiData() }
                    _uiState.update { it.copy(topRated = result, isLoading = false) }
                },
                onFailure = {
                    if (it is UnknownHostException) {
                        _uiState.update {
                            it.copy(
                                isError = true,
                                errorMessage = "Not internet connection",
                                isLoading = false
                            )
                        }
                    } else {
                        _uiState.update { it.copy(isError = true, isLoading = false) }
                    }
                }
            )
        }
    }

    fun loadPopular() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.fetchPopular()
            result.fold(
                onSuccess = {
                    val result = it.map { it.toHomeUiData() }
                    _uiState.update { it.copy(popular = result, isLoading = false) }
                },
                onFailure = {
                    if (it is UnknownHostException) {
                        _uiState.update {
                            it.copy(
                                isError = true,
                                errorMessage = "Not internet connection",
                                isLoading = false
                            )
                        }
                    } else {
                        _uiState.update { it.copy(isError = true, isLoading = false) }
                    }
                }
            )
        }
    }

    fun loadUpcoming() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.fetchUpcoming()
            result.fold(
                onSuccess = {
                    val upComingUiData = it.map { it.toHomeUiData() }
                    _uiState.update { it.copy(upcoming = upComingUiData, isLoading = false) }
                },
                onFailure = {
                    if (it is UnknownHostException) {
                        _uiState.update {
                            it.copy(
                                isError = true,
                                errorMessage = "Not internet connection",
                                isLoading = false
                            )
                        }
                    } else {
                        _uiState.update { it.copy(isError = true, isLoading = false) }
                    }
                }
            )
        }
    }
}