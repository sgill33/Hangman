package com.zybooks.hangman
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zybooks.hangman.ui.screens.start.StartScreen
import kotlinx.serialization.Serializable


sealed class Routes{
    @Serializable
    data object Start
}

@Composable
fun HangmanApp(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Start
    )
    {
        composable<Routes.Start> {
            StartScreen()
        }
    }

}
