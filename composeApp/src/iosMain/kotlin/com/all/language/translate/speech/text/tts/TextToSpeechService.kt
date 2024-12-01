package com.all.language.translate.speech.text.tts

import platform.AVFAudio.AVSpeechSynthesisVoice
import platform.AVFAudio.AVSpeechSynthesizer
import platform.AVFAudio.AVSpeechUtterance

actual class TextToSpeechService {
    private val synthesizer = AVSpeechSynthesizer()

    actual fun speak(text: String) : Int? {
        /*val utterance = AVSpeechUtterance(string = text)
        utterance.voice = AVSpeechSynthesisVoice()
        utterance.rate = 0.5F
        synthesizer.speakUtterance(utterance)
        */
        return 0
    }
}
