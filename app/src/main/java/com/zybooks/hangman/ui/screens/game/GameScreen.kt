package com.zybooks.hangman.ui.screens.game

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
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
            difficulty != null -> viewModel.setupGame(difficulty) // random word
            word != null -> viewModel.setWord(word) // player selected word
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hangman Game", fontSize = 20.sp) },
                actions = {
                    IconButton(onClick = { viewModel.updateShowQuitDialog(true) }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Quit Game",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween

        ) {
            Spacer(modifier = Modifier.height(0.dp))

            Text(
                text = "Guess the Word",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            )
            HangmanDrawing(viewModel.livesLeft)

            WordDisplay(viewModel.guessingWord, viewModel.clickedLetters)

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
    if (viewModel.showQuitDialog) {
        QuitGameDialog(
            onConfirmQuit = {
                viewModel.updateShowQuitDialog(false)
                navController.navigate(Routes.Start) // Navigate to home screen
            },
            onDismiss = { viewModel.updateShowQuitDialog(false) }
        )
    }
}

@Composable
fun QuitGameDialog(onConfirmQuit: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Quit Game") },
        text = { Text("Are you sure you want to quit the game?") },
        confirmButton = {
            TextButton(onClick = onConfirmQuit) {
                Text("Yes, Quit", color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
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
    val displayColor = MaterialTheme.colorScheme.primary
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
                // Display the letter (Initially '_' until guessed)
                val isGuessed = letter.uppercaseChar() in guessedLetters

                Text(
                    text = letter.toString().uppercase(),
                    fontSize = 32.sp,
                    color = if (isGuessed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
                )

                // Underline below the letter
                Canvas(
                    modifier = Modifier
                        .width(30.dp)
                        .height(2.dp)
                ) {
                    drawLine(
                        color = displayColor,
                        start = Offset(0f, size.height / 2),
                        end = Offset(size.width, size.height / 2),
                        strokeWidth = 4f
                    )
                }
            }
        }
    }
}

@Composable
fun HangmanDrawing(livesLeft: Int) {
    val gallowsColor = MaterialTheme.colorScheme.onSurface // Color for gallows
    val hangmanColor = MaterialTheme.colorScheme.primary   // Color for hangman figure

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 20.dp)
    ) {
        val strokeWidth = 8f
        val startX = size.width * 0.2f
        val endX = size.width * 0.8f
        val topY = 20f
        val centerX = size.width * 0.5f
        val baseY = size.height - 10f

        // Base of gallows
        drawLine(
            color = gallowsColor,
            start = Offset(startX, baseY),
            end = Offset(endX, baseY),
            strokeWidth = strokeWidth
        )

        // Vertical post
        drawLine(
            color = gallowsColor,
            start = Offset(startX, baseY),
            end = Offset(startX, topY),
            strokeWidth = strokeWidth
        )

        // Horizontal beam
        drawLine(
            color = gallowsColor,
            start = Offset(startX, topY),
            end = Offset(size.width * 0.5f, topY),
            strokeWidth = strokeWidth
        )

        // Rope
        drawLine(
            color = gallowsColor,
            start = Offset(size.width * 0.5f, topY),
            end = Offset(size.width * 0.5f, topY + 40f),
            strokeWidth = strokeWidth
        )


        when (livesLeft) {
            6 -> { /* Only gallows, no drawing */ }
            5 -> drawHead(centerX, topY, hangmanColor)
            4 -> { drawHead(centerX, topY, hangmanColor); drawBody(centerX, topY, strokeWidth, hangmanColor) }
            3 -> { drawHead(centerX, topY, hangmanColor); drawBody(centerX, topY, strokeWidth, hangmanColor); drawLeftArm(centerX, topY, strokeWidth, hangmanColor) }
            2 -> { drawHead(centerX, topY, hangmanColor); drawBody(centerX, topY, strokeWidth, hangmanColor); drawLeftArm(centerX, topY, strokeWidth, hangmanColor); drawRightArm(centerX, topY, strokeWidth, hangmanColor) }
            1 -> { drawHead(centerX, topY, hangmanColor); drawBody(centerX, topY, strokeWidth, hangmanColor); drawLeftArm(centerX, topY, strokeWidth, hangmanColor); drawRightArm(centerX, topY, strokeWidth, hangmanColor); drawLeftLeg(centerX, topY, strokeWidth, hangmanColor) }
            0 -> { drawHead(centerX, topY, hangmanColor); drawBody(centerX, topY, strokeWidth, hangmanColor); drawLeftArm(centerX, topY, strokeWidth, hangmanColor); drawRightArm(centerX, topY, strokeWidth, hangmanColor); drawLeftLeg(centerX, topY, strokeWidth, hangmanColor); drawRightLeg(centerX, topY, strokeWidth, hangmanColor) }
        }
    }
}

fun DrawScope.drawHead(centerX: Float, topY: Float, color: Color) {
    drawCircle(color, radius = 60f, center = Offset(centerX, topY + 100f))
}

fun DrawScope.drawBody(centerX: Float, topY: Float, strokeWidth: Float, color: Color) {
    drawLine(color, Offset(centerX, topY + 80f), Offset(centerX, topY + 375f), strokeWidth)
}

fun DrawScope.drawLeftArm(centerX: Float, topY: Float, strokeWidth: Float, color: Color) {
    drawLine(color, Offset(centerX, topY + 150f), Offset(centerX * 0.76f, topY + 300f), strokeWidth)
}

fun DrawScope.drawRightArm(centerX: Float, topY: Float, strokeWidth: Float, color: Color) {
    drawLine(color, Offset(centerX, topY + 150f), Offset(centerX * 1.2f, topY + 300f), strokeWidth)
}

fun DrawScope.drawLeftLeg(centerX: Float, topY: Float, strokeWidth: Float, color: Color) {
    drawLine(color, Offset(centerX, topY + 375f), Offset(centerX * 0.8f, topY + 500f), strokeWidth)
}

fun DrawScope.drawRightLeg(centerX: Float, topY: Float, strokeWidth: Float, color: Color) {
    drawLine(color, Offset(centerX, topY + 375f), Offset(centerX * 1.2f, topY + 500f), strokeWidth)
}


@Preview(showBackground = true)
@Composable
fun PreviewGameScreen() {
    val navController = rememberNavController()

    HangmanTheme {
        GameScreen(navController, difficulty = "easy", word = null)
    }
}


