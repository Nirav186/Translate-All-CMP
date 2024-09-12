package com.translate.ui.composable.selection

import cafe.adriel.voyager.core.model.ScreenModel
import com.translate.data.model.Language
import com.translate.data.storage.KeyValueStorage
import com.translate.data.storage.KeyValueStorageImpl
import com.translate.utils.Constant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update

class LanguageSelectionViewModel : ScreenModel {

    val searchText = MutableStateFlow("")

    private val _languageList = MutableStateFlow<List<Language>>(Constant.languageList)
    val languageList = _languageList.combine(searchText) { list, qry ->
        if (qry.isEmpty()) list else queryList(list, qry)
    }

    private val keyValueStorage: KeyValueStorage = KeyValueStorageImpl()

    private fun queryList(contacts: List<Language>, query: String): List<Language> {
        return contacts.filter { item ->
            val list = item.name.split(Regex("[.\\s]+"))
            list.any { it.startsWith(query, ignoreCase = true) }
        }
    }

    fun updateSearchQuery(text: String) {
        searchText.update { text }
    }

    fun saveLanguageIndex(selectedLanguage: Language?, isFrom: Boolean) {
        if (isFrom) {
            keyValueStorage.fromLanguageCode = (selectedLanguage ?: _languageList.value[0]).code
        } else {
            keyValueStorage.toLanguageCode = (selectedLanguage ?: _languageList.value[0]).code
        }
    }

}