package com.translate

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.android.gms.ads.MobileAds
import com.translate.data.networking.TranslationClient
import com.translate.data.networking.createHttpClient
import com.translate.database.getTranslateDatabase
import com.translate.tts.SpeechToTextService
import com.translate.tts.TextToSpeechService
import dev.icerock.moko.mvvm.dispatcher.eventsDispatcherOnMain
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect
import io.ktor.client.engine.okhttp.OkHttp

class MainActivity : ComponentActivity() {

    @SuppressLint("StaticFieldLeak")
    companion object {
        lateinit var context: Context
    }

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
        val permissionsController = PermissionsController(applicationContext)
        setContent {
//            BindEffect(permissionsController)
            App(
                historyDao = dao,
                client = TranslationClient(createHttpClient(OkHttp.create())),
                textToSpeechService = TextToSpeechService(this),
                speechToTextService = SpeechToTextService(this),
                permissionsController = permissionsController,
                eventsDispatcherOnMain = eventsDispatcherOnMain()
            )
        }
    }
}