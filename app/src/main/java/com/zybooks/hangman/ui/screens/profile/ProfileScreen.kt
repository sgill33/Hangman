package com.zybooks.hangman.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.zybooks.hangman.data.AppPreferences
import com.zybooks.hangman.data.AppStorage
import com.zybooks.hangman.ui.theme.HangmanTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val store = AppStorage(LocalContext.current)
    val appPrefs = store.appPreferencesFlow.collectAsStateWithLifecycle(AppPreferences())
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", fontSize = 20.sp) },
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
                .padding(30.dp, 60.dp),

            verticalArrangement = Arrangement.spacedBy(44.dp)
        ) {
            Text(
                text = "Games Played: ${appPrefs.value.gamesWon + appPrefs.value.gamesLost}",
                fontSize = 36.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Games Won: ${appPrefs.value.gamesWon}",
                fontSize = 36.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = "Games Lost: ${appPrefs.value.gamesLost}",
                fontSize = 36.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Dark Mode",
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Switch(
                    checked = appPrefs.value.darkMode,
                    onCheckedChange = { newTheme ->
                        coroutineScope.launch {
                            store.toggleTheme(newTheme)
                        }
                    }
                )
            }
        }
    }
}

// ✅ Preview with the app theme
@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    val navController = rememberNavController()
    HangmanTheme {
        ProfileScreen(navController)
    }
}
