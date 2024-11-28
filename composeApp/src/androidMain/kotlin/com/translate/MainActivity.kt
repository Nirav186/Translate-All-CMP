package com.translate

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.translate.data.networking.TranslationClient
import com.translate.data.networking.createHttpClient
import com.translate.database.getTranslateDatabase
import io.ktor.client.engine.okhttp.OkHttp

class MainActivity : ComponentActivity() {

    @SuppressLint("StaticFieldLeak")
    companion object {
        lateinit var context: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
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
            App(dao, client = TranslationClient(createHttpClient(OkHttp.create())))
        }
    }
}