package com.translate.ui.composable.dashboardOne

import cafe.adriel.voyager.core.model.ScreenModel
import com.translate.data.model.Language
import com.translate.data.storage.KeyValueStorage
import com.translate.data.storage.KeyValueStorageImpl

class DashBoardViewModel : ScreenModel {

    fun swapSelectedLanguage(selectedFromLang: Language, selectedToLang: Language) {
        val keyValueStorage: KeyValueStorage = KeyValueStorageImpl()
        val (fromLang, toLang) = Pair(selectedFromLang, selectedToLang)
        keyValueStorage.fromLanguageCode = toLang.code
        keyValueStorage.toLanguageCode = fromLang.code
    }
}