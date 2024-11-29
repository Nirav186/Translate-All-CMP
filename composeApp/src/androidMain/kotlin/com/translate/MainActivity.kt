package com.translate

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.translate.data.networking.DictionaryClient
import com.google.android.gms.ads.MobileAds
import com.translate.data.networking.TranslationClient
import com.translate.data.networking.createHttpClient
import com.translate.database.getTranslateDatabase
import com.translate.tts.SpeechToTextService
import com.translate.tts.TextToSpeechService
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
        MobileAds.initialize(this)
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