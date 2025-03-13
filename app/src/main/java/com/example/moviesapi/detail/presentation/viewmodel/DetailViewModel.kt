package com.example.moviesapi.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapi.detail.data.remote.dto.toDetailUiData
import com.example.moviesapi.detail.data.repository.DetailRepository
import com.example.moviesapi.detail.presentation.model.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val repository: DetailRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

    fun loadDetail(movieId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.fetchDetail(movieId = movieId)
            result.fold(
                onSuccess = {
                    val result = it.toDetailUiData()
                    _uiState.update { it.copy(listDetail = result, isLoading = false) }
                },
                onFailure = {
                    if (it is UnknownHostException) {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = "Not internet connection",
                                isError = true
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


