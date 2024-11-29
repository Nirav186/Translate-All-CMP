package com.all.language.translate.speech.text.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

actual class TextToSpeechService(private val context: Context) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.US
        }
    }

    actual fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}
