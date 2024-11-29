package com.all.language.translate.speech.text.ui.composable.history

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.all.language.translate.speech.text.data.model.History
import com.all.language.translate.speech.text.data.repo.HistoryRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryViewModel : ScreenModel {

    private val _historyList = MutableStateFlow<List<History>>(emptyList())
    val historyList = _historyList.asStateFlow()

    private val historyRepoImpl = HistoryRepoImpl()

    suspend fun getAllHistory() {
        screenModelScope.launch {
            historyRepoImpl.getAllHistory().collectLatest { list ->
                _historyList.update {
                    list
                }
            }
        }
    }

    suspend fun addHistory(history: History) =
        withContext(screenModelScope.coroutineContext) { historyRepoImpl.addHistory(history) }

    fun deleteHistory(history: History) =
        screenModelScope.launch { historyRepoImpl.deleteHistory(history) }

    fun updateHistory(history: History) =
        screenModelScope.launch { historyRepoImpl.updateHistory(history) }

    fun deleteAllHistory() = screenModelScope.launch { historyRepoImpl.deleteAllHistory() }

}