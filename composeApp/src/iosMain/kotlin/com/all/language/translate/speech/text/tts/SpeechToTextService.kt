package com.all.language.translate.speech.text.tts

import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioEngine
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryRecord
import platform.AVFAudio.AVAudioSessionModeMeasurement
import platform.AVFAudio.setActive
import platform.AVFoundation.*
import platform.Foundation.*
import platform.Speech.*

actual class SpeechToTextService {
    private val speechRecognizer = SFSpeechRecognizer()
    private var recognitionTask: SFSpeechRecognitionTask? = null

    @OptIn(ExperimentalForeignApi::class)
    actual fun startListening(onResult: (String) -> Unit, onError: (String) -> Unit) {
        val audioEngine = AVAudioEngine()
        val request = SFSpeechAudioBufferRecognitionRequest()

        // Ensure the audio session is set up for recognition
        val audioSession = AVAudioSession.sharedInstance()
        try {
            // Set the category to record and activate the session
            audioSession.setCategory(AVAudioSessionCategoryRecord, error = null)
            audioSession.setMode(AVAudioSessionModeMeasurement, error = null)
            audioSession.setActive(true, error = null)
        } catch (e: Exception) {
            onError("Audio session setup error: ${e.message}")
            return
        }

        val inputNode = audioEngine.inputNode
        if (inputNode == null) {
            onError("Audio engine input node is unavailable.")
            return
        }

        inputNode.installTapOnBus(0u,
            1024u, audioEngine.inputNode.outputFormatForBus(0u)) { buffer, _ ->
            request.appendAudioPCMBuffer(buffer!!)
        }

        audioEngine.prepare()


        try {
            audioEngine.startAndReturnError(null)
        } catch (e: Exception) {
            onError("Audio engine error: ${e.message}")
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
