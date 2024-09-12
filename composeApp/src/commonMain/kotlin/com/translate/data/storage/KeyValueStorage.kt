package com.translate.data.storage

import com.translate.data.model.Language
import kotlinx.coroutines.flow.Flow

interface KeyValueStorage {

    var fromLanguageCode: String

    var toLanguageCode: String

    val observableFromLanguage: Flow<Language>

    val observableToLanguage: Flow<Language>

    fun cleanStorage()
}