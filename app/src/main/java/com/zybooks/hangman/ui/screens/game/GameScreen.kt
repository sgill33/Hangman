package com.zybooks.hangman.ui.screens.game

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zybooks.hangman.ui.theme.HangmanTheme
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController, viewModel: GameViewModel = viewModel()) {
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

            Spacer(modifier = Modifier.height(20.dp))

            // TODO: Add Hangman game UI (word display, hangman figure, etc.)

            Spacer(modifier = Modifier.weight(1f))

            // Pass ViewModel to AlphabetGrid
            AlphabetGrid(viewModel)
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
                        enabled = !isClicked // Disable button after it's clicked
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


@Preview(showBackground = true)
@Composable
fun PreviewGameScreen() {
    val navController = rememberNavController()
    HangmanTheme {
        GameScreen(navController)
    }
}
