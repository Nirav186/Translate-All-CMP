package com.all.language.translate.speech.text.ui.composable.selection

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class LanguageSelectionScreen(private val isFrom: Boolean) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val languageSelectionViewModel = rememberScreenModel { LanguageSelectionViewModel() }
        LanguageSelection(
            languageSelectionViewModel, isFrom
        ) {
            navigator.pop()
        }
    }
}