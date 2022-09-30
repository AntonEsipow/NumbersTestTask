package com.bigtoapp.numberstesttask.numbers.presentation

sealed class UiState {

    // todo add views on fragments

    class Success: UiState() {

    }

    data class Error(private val message: String): UiState() {

    }
}