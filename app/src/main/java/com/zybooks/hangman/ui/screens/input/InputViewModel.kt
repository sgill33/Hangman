package com.zybooks.hangman.ui.screens.input

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class InputViewModel : ViewModel() {
    val inputText = mutableStateOf("")
    val isInputValid = mutableStateOf(false)

    fun onInputChanged(newInput: String) {
        // Filter input to allow only a-z (case insensitive)
        val filteredInput = newInput.filter { it.isLetter() }.lowercase()

        // Update input and validate length (2-9 characters)
        inputText.value = filteredInput
        isInputValid.value = filteredInput.length in 2..9
    }
}
