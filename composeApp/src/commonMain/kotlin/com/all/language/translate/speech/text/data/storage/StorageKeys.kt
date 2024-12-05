package com.all.language.translate.speech.text.data.storage

enum class StorageKeys {
    PERMISSION_REQUEST_COUNT,
    FROM_LANGUAGE,
    TO_LANGUAGE;

    val key get() = this.name
}