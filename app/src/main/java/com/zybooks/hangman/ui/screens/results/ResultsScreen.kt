package com.zybooks.hangman.ui.screens.results

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zybooks.hangman.Routes
import com.zybooks.hangman.data.AppStorage
import com.zybooks.hangman.ui.theme.HangmanTheme
import kotlinx.coroutines.launch

@Composable
fun ResultsScreen(navController: NavController, resultsRoute: Routes.Results) {
    val message = if (resultsRoute.playerWon) "You Win!" else "You Lost :("

    // Update Win/Loss values
    val store = AppStorage(LocalContext.current)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            if (resultsRoute.playerWon) {
                store.incrementGamesWon()
            } else {
                store.incrementGamesLost()
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Show "You Win" or "You Lost"
            Text(
                text = message,
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Show what the word was
            if (!resultsRoute.playerWon) {
                Text(
                    text = "The Word Was: ${resultsRoute.answer}",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // "Back to Home" button
            Button(
                onClick = { navController.navigate(Routes.Start) },
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
    val navController = rememberNavController()
    val previewResults = Routes.Results(answer = "example", playerWon = false) // Provide default values

    HangmanTheme {
        ResultsScreen(navController, previewResults)
    }
}

