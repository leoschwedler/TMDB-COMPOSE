package com.example.moviesapi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.moviesapi.data.remote.api.ApiService
import com.example.moviesapi.presentation.model.UiStateDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val service: ApiService): ViewModel() {

    val _uiState = MutableStateFlow(UiStateDetail())
    val uiState = _uiState.asStateFlow()


    suspend fun fetchDetail(id: String){
        try {
            _uiState.update { it.copy(isLoading = true) }
            val response = service.getDetailMovie(movieId = id)
            if (response.isSuccessful) {
                response.body()?.let { detailDTO ->
                    _uiState.update { it.copy(detailDTO = detailDTO, isLoading = false) }
                }
            } else {
                Log.e(
                    "DetailScreen",
                    "Error code ${response.code()} - Error Body - ${response.errorBody()}"
                )
            }
        } catch (e: Exception) {
            Log.e("DetailScreen", "Exception erro $e")
        }
    }
}