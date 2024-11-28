package com.translate.tts

expect class TextToSpeechService {
    fun speak(text: String)
}