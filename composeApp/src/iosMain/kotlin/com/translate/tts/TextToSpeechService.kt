package com.translate.tts

import platform.AVFoundation.AVSpeechSynthesizer
import platform.AVFoundation.AVSpeechUtterance
import platform.AVFoundation.AVSpeechSynthesisVoice

actual class TextToSpeechService {
    private val synthesizer = AVSpeechSynthesizer()

    actual fun speak(text: String) {
        val utterance = AVSpeechUtterance(text)
        utterance.voice = AVSpeechSynthesisVoice(language = "en-US")
        synthesizer.speakUtterance(utterance)
    }
}
