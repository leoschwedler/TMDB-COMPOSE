package com.example.moviesapi.detail.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapi.commom.model.Result
import com.example.moviesapi.detail.data.remote.dto.toDetailUiData
import com.example.moviesapi.detail.data.repository.DetailRepository
import com.example.moviesapi.detail.presentation.model.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val repository: DetailRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

    fun loadDetail(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            Log.d("DetailViewModel", "Fetching details for id: $id")
            val result = repository.fetchDetail(id)
            when (result) {
                is Result.Error -> {
                    _uiState.update {
                        Log.e("DetailViewModel", "Error fetching details: ${result.errorMessage}")
                        it.copy(
                            isLoading = false,
                            isError = true,
                            errorMessage = result.errorMessage
                        )
                    }
                }

                is Result.Success -> {
                    val detailUiData = result.data.toDetailUiData()
                    Log.d("DetailViewModel", "Successfully fetched details: $detailUiData")
                    _uiState.update { it.copy(detailUiData = detailUiData, isLoading = false) }
                }
            }
        }
    }
}


