package com.zybooks.hangman.ui.screens.game

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // Keep track of clicked letters
    private val _clickedLetters = mutableStateListOf<Char>()
    val clickedLetters: List<Char> get() = _clickedLetters

    // Function to update clicked letters
    fun onLetterClick(letter: Char) {
        if (letter !in _clickedLetters) {
            _clickedLetters.add(letter)
        }
    }
}
