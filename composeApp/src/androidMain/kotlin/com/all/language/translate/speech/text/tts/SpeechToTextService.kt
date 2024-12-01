package com.all.language.translate.speech.text.tts

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import java.util.Locale

actual class SpeechToTextService(private val context: Context) : RecognitionListener {
    private var speechRecognizer: SpeechRecognizer =
        SpeechRecognizer.createSpeechRecognizer(context)
    private var onResultCallback: ((String) -> Unit)? = null
    private var onErrorCallback: ((String) -> Unit)? = null

    init {
        speechRecognizer.setRecognitionListener(this)
    }

    actual fun startListening(onResult: (String) -> Unit, onError: (String) -> Unit) {
        onResultCallback = onResult
        onErrorCallback = onError

        if (!SpeechRecognizer.isRecognitionAvailable(context)) {
            Toast.makeText(context, "Service not available", Toast.LENGTH_SHORT).show()
        } else {
            val intent =
                Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                    putExtra(
                        RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                    )
                    putExtra(RecognizerIntent.EXTRA_LANGUAGE,/*Locale.getDefault()*/"en-US")
                    putExtra(RecognizerIntent.EXTRA_PROMPT, "  Speak Now")
                }
            (context as? Activity)?.startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
        }
    }

    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == Activity.RESULT_OK) {
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val recognizedText = results?.firstOrNull() ?: ""
            onResultCallback?.invoke(recognizedText)
        } else if (resultCode == Activity.RESULT_CANCELED) {
            onErrorCallback?.invoke("User canceled the speech input")
        } else {
            onErrorCallback?.invoke("Failed to recognize speech")
        }
    }

    companion object {
        private const val REQUEST_CODE_SPEECH_INPUT = 100
    }


    actual fun stopListening() {
        speechRecognizer.stopListening()
    }

    override fun onReadyForSpeech(params: Bundle?) {
        // Called when the speech recognizer is ready to listen
    }

    override fun onBeginningOfSpeech() {
        // Called when speech input starts
    }

    override fun onRmsChanged(rmsdB: Float) {
        // Called to provide the sound level in the input speech
    }

    override fun onBufferReceived(buffer: ByteArray?) {
        // Called when audio buffer is received
    }

    override fun onEndOfSpeech() {
        // Called when speech input ends
    }

    override fun onError(error: Int) {
        val message = when (error) {
            SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
            SpeechRecognizer.ERROR_CLIENT -> "Client-side error"
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
            SpeechRecognizer.ERROR_NETWORK -> "Network error"
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
            SpeechRecognizer.ERROR_NO_MATCH -> "No speech match found"
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "Recognition service is busy"
            SpeechRecognizer.ERROR_SERVER -> "Server error"
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input detected"
            else -> "Unknown error"
        }
        onErrorCallback?.invoke(message)
    }

    override fun onResults(bundle: Bundle?) {
        val results = bundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        onResultCallback?.invoke(results?.firstOrNull() ?: "")
    }

    override fun onPartialResults(partialResults: Bundle?) {
        // Called with partial speech recognition results
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        // Reserved for future events
    }
}
