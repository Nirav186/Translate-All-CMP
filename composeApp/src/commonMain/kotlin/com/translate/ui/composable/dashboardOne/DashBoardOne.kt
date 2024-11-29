package com.translate.ui.composable.dashboardOne

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.translate.ui.composable.dictionary.DictionaryScreen
import com.translate.ui.composable.favorite.FavoriteScreen
import com.translate.ui.composable.history.HistoryScreen
import com.translate.ui.composable.idioms.IdiomScreen
import com.translate.ui.composable.quotes.QuotesScreen
import com.translate.ui.composable.selection.LanguageSelectionScreen
import com.translate.ui.composable.translate.TranslateScreen
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory

class DashBoardScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val factory = rememberPermissionsControllerFactory()
        val controller = remember(factory) {
            factory.createPermissionsController()
        }

        BindEffect(controller)

        val dashBoardViewModel = rememberScreenModel { DashBoardViewModel(controller) }
        DashBoardContent(
            dashBoardViewModel = dashBoardViewModel,
            navigateToLanguageSelection = {
                navigator.push(LanguageSelectionScreen(it))
            },
            navigateToTranslateScreen = {
                navigator.push(TranslateScreen())
            },
            navigateToHistoryScreen = {
                navigator.push(HistoryScreen())
            },
            navigateToIdiomsListScreen = {
                navigator.push(IdiomScreen())
            },
            navigateToFavoriteScreen = {
                navigator.push(FavoriteScreen())
            },
            navigateToDictionaryScreen = {
                navigator.push(DictionaryScreen())
            },
            navigateToQuotesScreen = {
                navigator.push(QuotesScreen())
            }
        )
    }
}