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
import com.zybooks.hangman.ui.screens.players.PlayersScreen
import com.zybooks.hangman.ui.screens.input.InputScreen
import kotlinx.serialization.Serializable

sealed class Routes{
    @Serializable
    data object Start

    @Serializable
    data object Players

    @Serializable
    data object Input

    @Serializable
    data object Difficulty

    @Serializable
    data class GameWithDifficulty(val difficulty: String)

    @Serializable
    data class GameWithWord(val word: String)


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

        composable<Routes.Players> {
            PlayersScreen(navController)
        }

        composable<Routes.Difficulty> {
            DifficultyScreen(navController)
        }

        composable<Routes.Input> {
            InputScreen(navController)
        }

        composable<Routes.GameWithDifficulty> { backStackEntry ->
            val game : Routes.GameWithDifficulty = backStackEntry.toRoute()
            GameScreen(navController, difficulty = game.difficulty, word = null)
        }

        // ✅ Handle player-inputted word game
        composable<Routes.GameWithWord> { backStackEntry ->
            val game: Routes.GameWithWord = backStackEntry.toRoute()
            GameScreen(navController, difficulty = null, word = game.word)
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
