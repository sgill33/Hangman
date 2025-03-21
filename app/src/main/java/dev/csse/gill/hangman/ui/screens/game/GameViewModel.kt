package dev.csse.gill.hangman.ui.screens.game

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dev.csse.gill.hangman.data.WordDataSource

class GameViewModel : ViewModel() {

    var guessingWord by mutableStateOf("hangman") // Default word
        private set

    var livesLeft by mutableIntStateOf(6) // Default number of lives
        private set

    var showQuitDialog by mutableStateOf(false)

    // Keep track of clicked letters
    private val _clickedLetters = mutableStateListOf<Char>()
    val clickedLetters: List<Char> get() = _clickedLetters

    // Keep track of game state
    var isGameWon by mutableStateOf(false)
        private set

    var isGameLost by mutableStateOf(false)
        private set

    // Load words from WordDataSource
    private val wordList = WordDataSource.loadWords()

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

    private fun generateWord(difficulty: String) {
        val words = filterWordsByDifficulty(difficulty)
        if (words.isNotEmpty()) {
            guessingWord = words.random() // Pick a random word
        }
    }

    // Filter words based on difficulty
    private fun filterWordsByDifficulty(difficulty: String): List<String> {
        return when (difficulty) {
            "easy" -> wordList.filter { it.length in 2..4 }
            "medium" -> wordList.filter { it.length in 5..7 }
            else -> wordList.filter { it.length in 8..9 } // Hard mode
        }
    }

    fun setupGame(difficulty: String){
        generateWord(difficulty)
        livesLeft = 6
    }

    fun setWord(word: String){
        guessingWord = word
    }

    fun updateShowQuitDialog(show: Boolean){
        showQuitDialog = show
    }

}
