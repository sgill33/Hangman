package com.zybooks.hangman.ui.screens.results

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
fun ResultsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // "You Win" text centered
            Text(
                text = "You Win",
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            // "Back to Home" button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Back to Home",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

// ✅ Preview with the app theme
@Preview(showBackground = true)
@Composable
fun PreviewResultsScreen() {
    HangmanTheme {
        ResultsScreen()
    }
}
