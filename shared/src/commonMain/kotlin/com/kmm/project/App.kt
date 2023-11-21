package com.kmm.project

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.kmm.project.presentation.ui.theme.AppTheme
import com.kmm.project.presentation.ui.features.characters.CharactersScreen

@Composable
internal fun App() = AppTheme {
    Navigator(CharactersScreen())
}

