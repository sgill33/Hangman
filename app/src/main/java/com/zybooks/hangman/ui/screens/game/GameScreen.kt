package com.zybooks.hangman.ui.screens.game

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zybooks.hangman.ui.theme.HangmanTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zybooks.hangman.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController,
   difficulty: String?,
   word: String?,
   viewModel: GameViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        when {
            difficulty != null -> viewModel.setupGame(difficulty) // AI-selected word
            word != null -> viewModel.setWord(word) // Player-entered word
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hangman Game", fontSize = 20.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.1f))

            Text(
                text = "Guess the Word",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(400.dp))

            Text(
                text = "Lives Left: ${viewModel.livesLeft}",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.error
            )

            WordDisplay(viewModel.guessingWord, viewModel.clickedLetters)


            Spacer(modifier = Modifier.weight(1f))

            // Pass ViewModel to AlphabetGrid
            AlphabetGrid(viewModel)

            if (viewModel.isGameWon) {
                navController.navigate(Routes.Results(answer = viewModel.guessingWord, playerWon = true))
            }

            if (viewModel.isGameLost) {
                navController.navigate(Routes.Results(answer = viewModel.guessingWord, playerWon = false))
            }


        }
    }
}


@Composable
fun AlphabetGrid(viewModel: GameViewModel) {
    val alphabet = ('A'..'Z').toList() // List of letters A-Z
    val chunkedAlphabet = alphabet.chunked(7) // Split into rows of 7

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        chunkedAlphabet.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(3.dp),
                modifier = Modifier.padding(bottom = 6.dp)
            ) {
                row.forEach { letter ->
                    val isClicked = letter in viewModel.clickedLetters

                    Button(
                        onClick = { viewModel.onLetterClick(letter) },
                        modifier = Modifier.size(50.dp), // Square buttons
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isClicked) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.secondary, // Gray out when clicked
                            contentColor = if (isClicked) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSecondary
                        ),
                        contentPadding = PaddingValues(0.dp),
                        enabled = !isClicked && !viewModel.isGameLost
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = letter.toString(),
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WordDisplay(word: String, guessedLetters: List<Char>){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        word.forEach { letter ->

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 4.dp) // Space between letters
            ) {
                // Display the letter (Initially, it can be shown as '_' until guessed)
                val isGuessed = letter.uppercaseChar() in guessedLetters

                Text(
                    text = letter.toString().uppercase(),
                    fontSize = 32.sp,
                    color = if (isGuessed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
                )

                // Draw underline below the letter
                Canvas(
                    modifier = Modifier
                        .width(30.dp)
                        .height(2.dp) // Thin black underline
                ) {
                    drawLine(
                        color = Color.Black,
                        start = androidx.compose.ui.geometry.Offset(0f, size.height / 2),
                        end = androidx.compose.ui.geometry.Offset(size.width, size.height / 2),
                        strokeWidth = 4f
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewGameScreen() {
    val navController = rememberNavController()
    val gameRoute = Routes.GameWithDifficulty("medium") // ✅ Correct way to instantiate

    HangmanTheme {
        GameScreen(navController, difficulty = "easy", word = null)
    }
}


