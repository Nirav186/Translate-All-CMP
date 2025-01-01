package com.all.language.translate.speech.text

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.all.language.translate.speech.text.data.local.GetDatabaseBuilder
import com.all.language.translate.speech.text.data.networking.DictionaryClient
import com.all.language.translate.speech.text.data.networking.TranslationClient
import com.all.language.translate.speech.text.data.networking.createHttpClient
import com.all.language.translate.speech.text.tts.SpeechToTextService
import com.all.language.translate.speech.text.tts.TextToSpeechService
import com.all.language.translate.speech.text.utils.Constant.speechToTextService
import kotlinx.coroutines.Dispatchers


class MainActivity : ComponentActivity() {

    @SuppressLint("StaticFieldLeak")
    companion object {
        lateinit var context: Context
    }

    private val httpClient = createHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this

       /* MobileAds.initialize(this)*/
        val db = GetDatabaseBuilder(applicationContext)
            .builder()
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()

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
                database = db,
                translationClient = TranslationClient(httpClient),
                dictionaryClient = DictionaryClient(httpClient),
                textToSpeechService = TextToSpeechService(this),
                speechToTextService = SpeechToTextService(this)
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        speechToTextService.handleActivityResult(requestCode, resultCode, data)
    }
}