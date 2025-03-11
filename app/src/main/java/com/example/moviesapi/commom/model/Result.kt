package com.example.moviesapi.commom.model

sealed class Result<out T> {


    // Indica sucesso na operação e contém os dados obtidos.
    data class Success<out T>(val data: T) : Result<T>()

    // Indica que houve um erro, com uma mensagem descritiva.
    data class Error(val errorMessage: String) : Result<Nothing>()
}