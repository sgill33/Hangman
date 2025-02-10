package com.zybooks.hangman.ui.screens.start

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun StartScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Welcome text at the top
        Text(
            text = "Welcome to Hangman",
            fontSize = 28.sp,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(40.dp)) // Space between text and buttons

        // Start Game Button
        Button(
            //onClick = { navController.navigate(Routes.Difficulty.route) }, // Navigate to difficulty selection
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = {}
        ) {
            Text(text = "Start Game", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(20.dp)) // Space between buttons

        // View Profile Button
        Button(
            //onClick = { navController.navigate(Route.Profile.route) }, // Navigate to Profile
            modifier = Modifier.fillMaxWidth(0.6f),
            onClick = {}
        ) {
            Text(text = "View Profile", fontSize = 18.sp)
        }
    }
}

@Preview
@Composable
fun PreviewStartScreen(){
    StartScreen()
}
