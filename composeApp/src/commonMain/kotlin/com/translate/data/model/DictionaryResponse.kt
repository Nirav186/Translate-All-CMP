package com.translate.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class DictionaryResponseItem(
    @SerialName("license")
    val license: License? = null,
    @SerialName("meanings")
    val meanings: List<Meaning?>? = null,
    @SerialName("phonetic")
    val phonetic: String? = null,
    @SerialName("phonetics")
    val phonetics: List<Phonetic?>? = null,
    @SerialName("sourceUrls")
    val sourceUrls: List<String?>? = null,
    @SerialName("word")
    val word: String? = null
) {
    @Serializable
    data class License(
        @SerialName("name")
        val name: String? = null,
        @SerialName("url")
        val url: String? = null
    )

    @Serializable
    data class Meaning(
        @SerialName("antonyms")
        val antonyms: List<String?>? = null,
        @SerialName("definitions")
        val definitions: List<Definition?>? = null,
        @SerialName("partOfSpeech")
        val partOfSpeech: String? = null,
        @SerialName("synonyms")
        val synonyms: List<String?>? = null
    ) {
        @Serializable
        data class Definition(
            @SerialName("antonyms")
            val antonyms: List<String?>? = null,
            @SerialName("definition")
            val definition: String? = null,
            @SerialName("example")
            val example: String? = null,
            @SerialName("synonyms")
            val synonyms: List<String?>? = null
        )
    }

    @Serializable
    data class Phonetic(
        @SerialName("audio")
        val audio: String? = null,
        @SerialName("sourceUrl")
        val sourceUrl: String? = null,
        @SerialName("text")
        val text: String? = null
    )
}