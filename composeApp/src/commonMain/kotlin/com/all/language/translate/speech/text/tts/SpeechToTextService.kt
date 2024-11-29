package com.all.language.translate.speech.text.tts

expect class SpeechToTextService {
    fun startListening(onResult: (String) -> Unit, onError: (String) -> Unit)
    fun stopListening()
}
