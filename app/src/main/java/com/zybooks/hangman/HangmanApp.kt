package com.zybooks.hangman
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zybooks.hangman.ui.screens.difficulty.DifficultyScreen
import com.zybooks.hangman.ui.screens.game.GameScreen
import com.zybooks.hangman.ui.screens.profile.ProfileScreen
import com.zybooks.hangman.ui.screens.results.ResultsScreen
import com.zybooks.hangman.ui.screens.start.StartScreen
import kotlinx.serialization.Serializable

sealed class Routes{
    @Serializable
    data object Start

    @Serializable
    data object Difficulty

    @Serializable
    data object Game

    @Serializable
    data object Results

    @Serializable
    data object Profile
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
            StartScreen(navController)
        }

        composable<Routes.Difficulty> {
            DifficultyScreen(navController)
        }

        composable<Routes.Game> {
            GameScreen(navController)
        }

        composable<Routes.Results> {
            ResultsScreen(navController)
        }

        composable<Routes.Profile> {
            ProfileScreen(navController)
        }
    }


}
