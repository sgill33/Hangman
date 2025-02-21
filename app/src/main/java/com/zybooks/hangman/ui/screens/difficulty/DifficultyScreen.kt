package com.zybooks.hangman.ui.screens.difficulty

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zybooks.hangman.Routes
import com.zybooks.hangman.ui.theme.HangmanTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultyScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Difficulty", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
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
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            // Title
            Text(
                text = "Choose your difficulty",
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 32.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Difficulty Buttons
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                DifficultyButton("Easy", MaterialTheme.colorScheme.primary) {
                    navController.navigate(Routes.GameWithDifficulty("easy"))
                }
                Spacer(modifier = Modifier.height(40.dp))
                DifficultyButton("Medium", MaterialTheme.colorScheme.secondary) {
                    navController.navigate(Routes.GameWithDifficulty("medium"))
                }
                Spacer(modifier = Modifier.height(40.dp))
                DifficultyButton("Hard", MaterialTheme.colorScheme.tertiary) {
                    navController.navigate(Routes.GameWithDifficulty("hard"))
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun DifficultyButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(80.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(
            text = text,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

// ✅ Preview with the app theme
@Preview(showBackground = true)
@Composable
fun PreviewDifficultyScreen() {
    val navController = rememberNavController()
    HangmanTheme {
        DifficultyScreen(navController)
    }
}
