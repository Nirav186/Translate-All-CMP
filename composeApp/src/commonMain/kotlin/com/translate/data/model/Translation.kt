package com.translate.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Translation(
    @SerialName("dict")
    val dict: List<Dict?>? = listOf(),
    @SerialName("sentences")
    val sentences: List<Sentence?>? = listOf(),
    @SerialName("spell")
    val spell: Spell? = Spell(),
    @SerialName("src")
    val src: String? = ""
){
    @Serializable
    data class Dict(
        @SerialName("base_form")
        val baseForm: String? = "",
        @SerialName("entry")
        val entry: List<Entry?>? = listOf(),
        @SerialName("pos")
        val pos: String? = "",
        @SerialName("pos_enum")
        val posEnum: Int? = 0,
        @SerialName("terms")
        val terms: List<String?>? = listOf()
    ){
        @Serializable
        data class Entry(
            @SerialName("reverse_translation")
            val reverseTranslation: List<String?>? = listOf(),
            @SerialName("score")
            val score: Double? = 0.0,
            @SerialName("word")
            val word: String? = ""
        )
    }

    @Serializable
    data class Sentence(
        @SerialName("backend")
        val backend: Int? = 0,
        @SerialName("orig")
        val orig: String? = "",
        @SerialName("trans")
        val trans: String? = ""
    )

    @Serializable
    class Spell
}

