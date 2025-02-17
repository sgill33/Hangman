package com.zybooks.hangman.ui.screens.players

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zybooks.hangman.ui.theme.HangmanTheme
import androidx.navigation.compose.rememberNavController
import com.zybooks.hangman.Routes
import androidx.navigation.NavController

@Composable
fun PlayersScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text at the top
        Text(
            text = "How Many Players?",
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 75.dp) // Adds space at the top
        )

        // Spacer to push buttons to the middle
        Spacer(modifier = Modifier.weight(1f))

        // Buttons section (centered vertically)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Start Game Button
            Button(
                onClick = {navController.navigate(Routes.Difficulty) },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(75.dp)
                ,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = "One Player",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

            Spacer(modifier = Modifier.height(20.dp)) // Space between buttons

            // View Profile Button
            Button(
                onClick = {navController.navigate(Routes.Input)},
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(75.dp)
                ,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
            ) {
                Text(
                    text = "Two Players",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }

        // Spacer to push everything to the right position
        Spacer(modifier = Modifier.weight(1f))
    }
}

// ✅ Preview with the app theme
@Preview(showBackground = true)
@Composable
fun PreviewPlayersScreen() {
    val navController = rememberNavController()
    HangmanTheme {
        PlayersScreen(navController)
    }
}
