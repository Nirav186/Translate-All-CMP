package com.all.language.translate.speech.text.data.storage

import com.all.language.translate.speech.text.data.model.Language
import kotlinx.coroutines.flow.Flow

interface KeyValueStorage {

    var fromLanguageCode: String

    var toLanguageCode: String

    val observableFromLanguage: Flow<Language>

    val observableToLanguage: Flow<Language>

    var isPermissionRequested: Int

    fun cleanStorage()
}