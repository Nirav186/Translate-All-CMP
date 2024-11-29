package com.all.language.translate.speech.text.tts

import platform.AVFoundation.*
import platform.Foundation.*
import platform.Speech.*

actual class SpeechToTextService {
    private val speechRecognizer = SFSpeechRecognizer()
    private var recognitionTask: SFSpeechRecognitionTask? = null

    actual fun startListening(onResult: (String) -> Unit, onError: (String) -> Unit) {
        val audioEngine = AVAudioEngine()
        val request = SFSpeechAudioBufferRecognitionRequest()

        val inputNode = audioEngine.inputNode
        inputNode.installTapOnBus(0, 1024, audioEngine.inputNode.outputFormatForBus(0)) { buffer, _ ->
            request.appendAudioPCMBuffer(buffer)
        }

        audioEngine.prepare()
        try {
            audioEngine.start()
        } catch (e: Exception) {
            onError("Audio engine error: ${e.localizedMessage}")
            return
        }

        recognitionTask = speechRecognizer?.recognitionTaskWithRequest(request) { result, error ->
            if (error != null || result == null) {
                onError("Speech recognition error: ${error?.localizedDescription}")
                return@recognitionTaskWithRequest
            }
            onResult(result.bestTranscription.formattedString)
        }
    }

    actual fun stopListening() {
        recognitionTask?.cancel()
        recognitionTask = null
    }
}
