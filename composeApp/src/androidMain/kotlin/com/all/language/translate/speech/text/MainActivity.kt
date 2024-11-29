package com.all.language.translate.speech.text

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.all.language.translate.speech.text.data.networking.DictionaryClient
import com.all.language.translate.speech.text.data.networking.TranslationClient
import com.all.language.translate.speech.text.data.networking.createHttpClient
import com.all.language.translate.speech.text.database.getTranslateDatabase
import com.all.language.translate.speech.text.tts.SpeechToTextService
import com.all.language.translate.speech.text.tts.TextToSpeechService
import io.ktor.client.engine.okhttp.OkHttp

class MainActivity : ComponentActivity() {

    @SuppressLint("StaticFieldLeak")
    companion object {
        lateinit var context: Context
    }

    private val httpClient = createHttpClient(OkHttp.create())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
//        MobileAds.initialize(this)
        val dao = getTranslateDatabase(applicationContext).getHistoryDao()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )
        setContent {
            App(
                historyDao = dao,
                translationClient = TranslationClient(httpClient),
                dictionaryClient = DictionaryClient(httpClient),
                textToSpeechService = TextToSpeechService(this),
                speechToTextService = SpeechToTextService(this)
            )
        }
    }
}