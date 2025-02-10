package com.zybooks.hangman.ui.screens.difficulty

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zybooks.hangman.ui.theme.HangmanTheme

@Composable
fun DifficultyScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title at the top
        Text(
            text = "Choose your difficulty",
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 32.dp) // Adds space at the top
        )

        // Spacer to push buttons to the middle
        Spacer(modifier = Modifier.weight(1f))

        // Difficulty Buttons (Centered)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Easy Button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(80.dp), // Large button
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Easy",
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Medium Button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(80.dp), // Large button
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = "Medium",
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Hard Button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(80.dp), // Large button
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
            ) {
                Text(
                    text = "Hard",
                    fontSize = 22.sp,
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
fun PreviewDifficultyScreen() {
    HangmanTheme {
        DifficultyScreen()
    }
}
