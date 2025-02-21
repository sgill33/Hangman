package com.zybooks.hangman.ui.theme

import android.os.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zybooks.hangman.data.AppPreferences
import com.zybooks.hangman.data.AppStorage

// 🎨 Dark Theme (Game-Like: Dark Background, Neon Colors)
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFE63946), // Bright Red (for dramatic effect)
    secondary = Color(0xFFF4A261), // Orange (for highlights)
    tertiary = Color(0xFFFFC107), // Neon Yellow
    background = Color(0xFF121212), // Almost black for game intensity
    surface = Color(0xFF1E1E1E), // Dark Gray for UI elements
    onPrimary = Color.White, // Text on red buttons
    onSecondary = Color.Black, // Text on orange buttons
    onTertiary = Color.Black, // Text on yellow buttons
    onBackground = Color.White, // White text for dark background
    onSurface = Color.White // White text for UI elements
)

// 🎨 Light Theme (Game-Like: Playful Colors)
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0077B6), // Bright Blue (main theme)
    secondary = Color(0xFFFFA500), // Orange (highlights)
    tertiary = Color(0xFFFFC107), // Yellow (for extra vibrance)
    background = Color(0xFFFFFFFF), // White background
    surface = Color(0xFFF0F0F0), // Light gray for UI elements
    onPrimary = Color.White, // Text on blue buttons
    onSecondary = Color.Black, // Text on orange buttons
    onTertiary = Color.Black, // Text on yellow buttons
    onBackground = Color.Black, // Black text for readability
    onSurface = Color.Black // Black text for UI elements
)

@Composable
fun HangmanTheme(
    content: @Composable () -> Unit
) {
    val store = AppStorage(LocalContext.current)
    val appPrefs = store.appPreferencesFlow.collectAsStateWithLifecycle(AppPreferences())

    val isDarkMode = appPrefs.value.darkMode

    val colorScheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDarkMode) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        isDarkMode -> DarkColorScheme
        else -> LightColorScheme
    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
