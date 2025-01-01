package com.all.language.translate.speech.text

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.all.language.translate.speech.text.data.local.GetDatabaseBuilder
import com.all.language.translate.speech.text.data.networking.DictionaryClient
import com.all.language.translate.speech.text.data.networking.TranslationClient
import com.all.language.translate.speech.text.data.networking.createHttpClient
import com.all.language.translate.speech.text.tts.SpeechToTextService
import com.all.language.translate.speech.text.tts.TextToSpeechService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

fun MainViewController() = ComposeUIViewController {
    val db = remember {
        GetDatabaseBuilder()
            .builder().setQueryCoroutineContext(Dispatchers.IO).build()

    }
    val httpClient = createHttpClient()
    val translationClient = TranslationClient(httpClient)
    val dictionaryClient = DictionaryClient(httpClient)
    val textToSpeechService = TextToSpeechService()
    val speechToTextService = SpeechToTextService()
    App(
        database = db,
        translationClient = translationClient,
        dictionaryClient = dictionaryClient,
        textToSpeechService = textToSpeechService,
        speechToTextService = speechToTextService
    )
}