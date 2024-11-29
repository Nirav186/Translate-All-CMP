package com.all.language.translate.speech.text.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    @SerialName("name") var name: String,
    @SerialName("dates") var dates: String ,
    @SerialName("quotes") var quotes: ArrayList<String> = arrayListOf(),
    var imageRes: Int? = null
)