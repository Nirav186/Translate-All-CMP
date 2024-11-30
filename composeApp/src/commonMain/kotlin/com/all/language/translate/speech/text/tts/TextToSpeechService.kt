package com.all.language.translate.speech.text.tts

expect class TextToSpeechService {
    fun speak(text: String) : Int?
}