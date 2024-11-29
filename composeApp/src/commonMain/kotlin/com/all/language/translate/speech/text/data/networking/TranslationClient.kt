package com.all.language.translate.speech.text.data.networking

import com.all.language.translate.speech.text.data.model.Translation
import com.all.language.translate.speech.text.utils.NetworkError
import com.all.language.translate.speech.text.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class TranslationClient(
    private val httpClient: HttpClient
) {

    suspend fun translateWords(
        originalText: String,
        srcLanguage: String,
        targetLanguage: String
    ): Result<Translation, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "https://translate.googleapis.com/translate_a/single"
            ) {
                parameter("client", "gtx")
                parameter("sl", srcLanguage)
                parameter("tl", targetLanguage)
                parameter("dt", "t")
                parameter("dt", "bd")
                parameter("dj", "1")
                parameter("q", originalText)
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val censoredText = response.body<Translation>()
                Result.Success(censoredText)
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}