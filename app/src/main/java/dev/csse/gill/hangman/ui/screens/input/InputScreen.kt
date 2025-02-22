package dev.csse.gill.hangman.ui.screens.input

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.csse.gill.hangman.Routes
import dev.csse.gill.hangman.ui.theme.HangmanTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(navController: NavController, inputViewModel: InputViewModel = viewModel()) {
    val inputText by inputViewModel.inputText
    val isInputValid by inputViewModel.isInputValid

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Enter a Word", fontSize = 20.sp) },
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Player 1: Input a String (2-9 Characters)",
                fontSize = 19.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = inputText,
                onValueChange = { inputViewModel.onInputChanged(it) },
                label = { Text("Enter Word") },
                modifier = Modifier.fillMaxWidth(),
                isError = !isInputValid
            )

            if (!isInputValid && inputText.isNotEmpty()) {
                Text(
                    text = "Input must be 2-9 letters (a-z only).",
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Routes.GameWithWord(inputText)) },
                enabled = isInputValid,
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text(text = "Start Game", fontSize = 18.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInputScreen() {
    val navController = rememberNavController()
    HangmanTheme {
        InputScreen(navController)
    }
}

