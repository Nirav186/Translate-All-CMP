package com.all.language.translate.speech.text.data.storage

enum class StorageKeys {
    FROM_LANGUAGE,
    TO_LANGUAGE;

    val key get() = this.name
}