package com.translate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class History(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val fromLang: String,
    val toLang: String,
    val originalText: String,
    val translateText: String,
    val isFavorite: Boolean = false,
    val timeStamp: Long = Clock.System.now().epochSeconds
) {

    fun Long.toReadableDateTime(): String {
        val instant = Instant.fromEpochSeconds(this)
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return dateTime.toString()  // Customize this as needed, e.g., format to "yyyy-MM-dd HH:mm"
    }

    companion object {
        fun empty() =
            History(id = 0, fromLang = "", toLang = "", originalText = "", translateText = "")
    }
}

