package com.translate

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.translate.data.local.HistoryDao
import com.translate.data.networking.TranslationClient
import com.translate.theme.TranslateAllLanguageTheme
import com.translate.tts.SpeechToTextService
import com.translate.tts.TextToSpeechService
import com.translate.ui.composable.splash.SplashScreen
import com.translate.utils.Constant
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    historyDao: HistoryDao,
    client: TranslationClient,
    textToSpeechService: TextToSpeechService,
    speechToTextService: SpeechToTextService
) {
    Constant.historyDao = historyDao
    Constant.client = client
    Constant.textToSpeechService = textToSpeechService
    Constant.speechToTextService = speechToTextService
    TranslateAllLanguageTheme {
        Navigator(SplashScreen())
    }
}