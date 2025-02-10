package com.zybooks.hangman.ui.screens.start

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
fun StartScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Text at the top
        Text(
            text = "Welcome to Hangman",
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
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(75.dp)
                ,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Start Game",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.height(20.dp)) // Space between buttons

            // View Profile Button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(75.dp)
                ,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = "View Profile",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSecondary
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
fun PreviewStartScreen() {
    HangmanTheme {
        StartScreen()
    }
}
