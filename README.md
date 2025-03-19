# Hangman Mobile App

Hangman is a mobile application for Android, developed using Kotlin and Jetpack Compose. It features two gameplay modes: Single-Player and Two-Player.

### Single-Player:
The single-player mode has 3 different difficulties:
- Easy (2-4 Characters)
- Medium (5-7 Characters)
- Hard (8-9 Characters)

Words are randomly selected from the [New General Service List (NGSL)](https://www.newgeneralservicelist.com/new-general-service-list), a collection of essential English vocabulary for second language learners. This list was chosen specifically because it includes common, familiar words, unlike more obscure alternatives (e.g., NLTK Word Corpus). 

A player has 6 lives to guess the word. With every incorrect guess, one additional part of the hangman figure is drawn. After successfully guessing the word, players have the option to start a new game. If unsuccessful, the correct word is revealed before a player starts again.

### Two-Player:
The Two-Player mode closely mirrors the Single-Player experience, with a key difference: Player One manually inputs a custom word, after which the device is handed to Player Two, who attempts to guess it. Gameplay then follows the standard rules established in Single-Player mode.

## Design
Figma Design Linked [Here](https://www.figma.com/design/XDjYhywrMmiOZVIPhKsyaD/Hangman-Wireframe?node-id=2003-446&t=PXkJpgpnz7iHu910-1)

## Libraries & Dependencies
- **Drawing**: Utilized the AndroidX Graphics Library to dynamically render the hangman visuals.
- **Persistent Storage**: DataStore used for tracking player statistics and theme preferences.
- **Minimum Android SDK Version**: 24
- **Target Android SDK Version**: 35

## Additional Features
- **User Profile & Statistics**: Players can view their lifetime statistics (wins and losses) in their dedicated profile page. Stats are persistently stored using DataStore.
- **Dark Mode Toggle**: A dark mode feature is available, allowing users to switch themes based on their preferences. The selected theme mode is saved using DataStore.
