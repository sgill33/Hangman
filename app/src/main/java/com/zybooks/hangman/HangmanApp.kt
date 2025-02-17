package com.zybooks.hangman
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
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
    data class Game(
        val difficulty: String
    )

    @Serializable
    data class Results(
        val answer: String,
        val playerWon: Boolean
    )

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

        composable<Routes.Game> { backStackEntry ->
            val game : Routes.Game = backStackEntry.toRoute()
            GameScreen(navController, game)
        }

        composable<Routes.Results> { backStackEntry ->
            val results : Routes.Results = backStackEntry.toRoute()
            ResultsScreen(navController, results)
        }

        composable<Routes.Profile> {
            ProfileScreen(navController)
        }
    }


}
