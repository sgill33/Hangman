package com.zybooks.hangman.ui.screens.game

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var guessingWord by mutableStateOf("hangman") // Default word
        private set

    var livesLeft by mutableStateOf(6) // Default number of lives
        private set

    // Keep track of clicked letters
    private val _clickedLetters = mutableStateListOf<Char>()
    val clickedLetters: List<Char> get() = _clickedLetters

    // Keep track of game state
    var isGameWon by mutableStateOf(false)
        private set

    var isGameLost by mutableStateOf(false)
        private set

    // Function to update clicked letters
    fun onLetterClick(letter: Char) {
        if (letter !in _clickedLetters) {
            _clickedLetters.add(letter)

            // Check if the guessed letter is in the word
            if (letter.uppercaseChar() !in guessingWord.uppercase()) {
                livesLeft-- // Deduct life for wrong guess
                if (livesLeft <= 0) {
                    isGameLost = true // Trigger game over condition
                }
            } else {
                // Check if all letters in the word are guessed
                if (guessingWord.all { it.uppercaseChar() in _clickedLetters }) {
                    isGameWon = true // Trigger win popup
                }
            }
        }
    }


    private fun generateWord(){
        guessingWord = "elephants"
    }

    private fun setDifficulty(difficulty: String){
        if (difficulty == "easy"){
            return
        }
        else if (difficulty == "medium"){
            livesLeft = 4
        }
        else{
            livesLeft = 3
        }
    }

    fun setupGame(difficulty: String){
        generateWord()
        setDifficulty(difficulty)
    }

}
