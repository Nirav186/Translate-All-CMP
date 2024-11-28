package com.translate.ui.composable.translate

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.translate.data.model.History
import com.translate.data.model.Language
import com.translate.data.repo.HistoryRepoImpl
import com.translate.data.storage.KeyValueStorage
import com.translate.data.storage.KeyValueStorageImpl
import com.translate.utils.Constant
import com.translate.utils.onSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TranslateViewModel : ScreenModel {

    private val _history = MutableStateFlow(
        History(
            id = 0,
            fromLang = "",
            toLang = "",
            originalText = "",
            translateText = ""
        )
    )
    val history = _history.asStateFlow()

    private val historyRepoImpl = HistoryRepoImpl()
    private val keyValueStorage: KeyValueStorage = KeyValueStorageImpl()

    fun init(history: History?, fromLang: Language, toLang: Language) {
        history?.let {
            _history.update { history }
        } ?: run {
            _history.update {
                History(
                    id = 0,
                    fromLang = fromLang.name,
                    toLang = toLang.name,
                    originalText = "",
                    translateText = ""
                )
            }
        }
    }

    private suspend fun addHistory(history: History) =
        withContext(screenModelScope.coroutineContext) { historyRepoImpl.addHistory(history) }

    private fun updateHistory(history: History) =
        screenModelScope.launch { historyRepoImpl.updateHistory(history) }

    fun updateOriginalText(q: String) {
        _history.update { _history.value.copy(originalText = q) }
    }

    fun translate() {
        //todo: implement translate api and update history then only perform below add history operation
        //todo:remove below line it's only for testing
        screenModelScope.launch {
            val translateWords = Constant.client.translateWords(
                originalText = _history.value.originalText,
                srcLanguage = keyValueStorage.fromLanguageCode.ifEmpty { Constant.languageList.first().code },
                targetLanguage = keyValueStorage.toLanguageCode.ifEmpty { Constant.languageList.first().code }
            )
            translateWords.onSuccess { translation ->
                _history.update {
                    _history.value.copy(
                        translateText = if (translation.dict.isNullOrEmpty().not()) {
                            translation.dict?.firstOrNull()?.entry?.firstOrNull()?.word
                                ?: _history.value.originalText
                        } else if (translation.sentences.isNullOrEmpty().not()) {
                            translation.sentences?.firstOrNull()?.trans
                                ?: _history.value.originalText
                        } else {
                            _history.value.originalText
                        }
                    )

                }
                CoroutineScope(Dispatchers.IO).launch {
                    val id = async { addHistory(_history.value) }.await()
                    if (id != -1L) {
                        _history.value.id = id.toInt()
                    }
                }
            }
        }
    }

    fun clear() {
        screenModelScope.launch {
            _history.update {
                _history.value.copy(originalText = "", translateText = "")
            }
        }
    }

    fun toggleFavorite() {
        screenModelScope.launch {
            _history.update { _history.value.copy(isFavorite = !_history.value.isFavorite) }
            updateHistory(_history.value)
        }
    }

    fun shareText() {
        com.translate.shareText(_history.value.translateText)
    }

}