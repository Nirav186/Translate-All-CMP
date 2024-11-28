package com.translate

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.translate.data.local.HistoryDao
import com.translate.data.networking.TranslationClient
import com.translate.theme.TranslateAllLanguageTheme
import com.translate.ui.composable.splash.SplashScreen
import com.translate.utils.Constant
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(historyDao: HistoryDao, client: TranslationClient) {
    Constant.historyDao = historyDao
    Constant.client = client
    TranslateAllLanguageTheme {
        Navigator(SplashScreen())
    }
}