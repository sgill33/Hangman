package com.zybooks.hangman.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class AppPreferences(
    val gamesWon: Int = 0,
    val gamesLost: Int = 0,
    val darkMode: Boolean = false
)

class AppStorage(private val context: Context){
    companion object{
        private val Context.dataStore: DataStore<Preferences> by
                preferencesDataStore("app_storage")
    }

    private object PreferenceKeys {
        val GAMES_WON = intPreferencesKey("gamesWon")
        val GAMES_LOST = intPreferencesKey("gamesLost")
        val DARK_MODE = booleanPreferencesKey("darkMode")
    }

    val appPreferencesFlow: Flow<AppPreferences> =
        context.dataStore.data.map { prefs ->
            val gamesWon = prefs[PreferenceKeys.GAMES_WON] ?: 0
            val gamesLost = prefs[PreferenceKeys.GAMES_LOST] ?: 0
            val darkMode = prefs[PreferenceKeys.DARK_MODE] ?: false

            AppPreferences(gamesWon,gamesLost, darkMode)
        }

    suspend fun incrementGamesWon() {
        context.dataStore.edit { prefs ->
            val currentWins = prefs[PreferenceKeys.GAMES_WON] ?: 0
            prefs[PreferenceKeys.GAMES_WON] = currentWins + 1
        }
    }

    suspend fun incrementGamesLost() {
        context.dataStore.edit { prefs ->
            val currentWins = prefs[PreferenceKeys.GAMES_LOST] ?: 0
            prefs[PreferenceKeys.GAMES_LOST] = currentWins + 1
        }
    }

    suspend fun toggleTheme(isDark: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[PreferenceKeys.DARK_MODE] = isDark
        }
    }


}
