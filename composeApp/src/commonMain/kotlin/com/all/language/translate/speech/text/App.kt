package com.all.language.translate.speech.text

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.all.language.translate.speech.text.data.local.HistoryDao
import com.all.language.translate.speech.text.data.networking.DictionaryClient
import com.all.language.translate.speech.text.data.networking.TranslationClient
import com.all.language.translate.speech.text.theme.TranslateAllLanguageTheme
import com.all.language.translate.speech.text.tts.SpeechToTextService
import com.all.language.translate.speech.text.tts.TextToSpeechService
import com.all.language.translate.speech.text.ui.composable.splash.SplashScreen
import com.all.language.translate.speech.text.utils.Constant
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    historyDao: HistoryDao,
    translationClient: TranslationClient,
    textToSpeechService: TextToSpeechService,
    speechToTextService: SpeechToTextService,
    dictionaryClient: DictionaryClient,
) {
    Constant.historyDao = historyDao
    Constant.dictionaryClient = dictionaryClient
    Constant.translationClient = translationClient
    Constant.textToSpeechService = textToSpeechService
    Constant.speechToTextService = speechToTextService
    TranslateAllLanguageTheme {
        Navigator(SplashScreen())
    }
}