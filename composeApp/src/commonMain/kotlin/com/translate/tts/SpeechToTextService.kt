package com.translate.tts

expect class SpeechToTextService {
    fun startListening(onResult: (String) -> Unit, onError: (String) -> Unit)
    fun stopListening()
}
