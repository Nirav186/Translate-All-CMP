package com.all.language.translate.speech.text.data.storage

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringFlow
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import com.all.language.translate.speech.text.data.model.Language
import com.all.language.translate.speech.text.utils.Constant
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapLatest

class KeyValueStorageImpl : KeyValueStorage {
    private val settings: Settings by lazy { Settings() }
    private val observableSettings: ObservableSettings by lazy { settings as ObservableSettings }

    override var fromLanguageCode: String
        get() = settings[StorageKeys.FROM_LANGUAGE.key] ?: ""
        set(value) {
            settings[StorageKeys.FROM_LANGUAGE.key] = value
        }

    override var toLanguageCode: String
        get() = settings[StorageKeys.TO_LANGUAGE.key] ?: ""
        set(value) {
            settings[StorageKeys.TO_LANGUAGE.key] = value
        }

    @OptIn(ExperimentalCoroutinesApi::class, ExperimentalSettingsApi::class)
    override val observableFromLanguage: Flow<Language>
        get() = observableSettings.getStringFlow(StorageKeys.FROM_LANGUAGE.key, "")
            .mapLatest { languageCode ->
                Constant.languageList.find { it.code == languageCode }
            }
            .filterNotNull()

    @OptIn(ExperimentalSettingsApi::class, ExperimentalCoroutinesApi::class)
    override val observableToLanguage: Flow<Language>
        get() = observableSettings.getStringFlow(StorageKeys.TO_LANGUAGE.key, "")
            .mapLatest { languageCode ->
                Constant.languageList.find { it.code == languageCode }
            }
            .filterNotNull()

    // clean all the stored values
    override fun cleanStorage() {
        settings.clear()
    }
}