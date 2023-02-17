package com.bigtoapp.numberstesttask.numbers.presentation

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

sealed class UiState {

    abstract fun apply(inputLayout: CustomTextInputLayout, textInputEditText: CustomTextInputEditText)

    class Success: UiState() {
        override fun apply(
            inputLayout: CustomTextInputLayout,
            textInputEditText: CustomTextInputEditText
        ) = textInputEditText.showText("")

    }

    abstract class AbstractError(
        private val message: String,
        private val errorEnabled: Boolean
    ) : UiState() {
        override fun apply(
            inputLayout: CustomTextInputLayout,
            textInputEditText: CustomTextInputEditText
        ) = with(inputLayout) {
            changeErrorEnabled(errorEnabled)
            showError(message)
        }
    }

    data class ShowError(private val message: String): AbstractError(message, true)
    class ClearError : AbstractError("", false)
}