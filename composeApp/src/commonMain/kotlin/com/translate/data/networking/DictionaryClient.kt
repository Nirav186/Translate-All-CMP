package com.translate.data.networking

import com.translate.data.model.DictionaryResponseItem
import com.translate.utils.NetworkError
import com.translate.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

class DictionaryClient(
    private val httpClient: HttpClient
) {

    suspend fun findDictionaryForWord(
        word: String
    ): Result<Array<DictionaryResponseItem>, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "https://api.dictionaryapi.dev/api/v2/entries/en/$word"
            )
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when (response.status.value) {
            in 200..299 -> {
                val result = response.body<Array<DictionaryResponseItem>>()
                Result.Success(result)
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