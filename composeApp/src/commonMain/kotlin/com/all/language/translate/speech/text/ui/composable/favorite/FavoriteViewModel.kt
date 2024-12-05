package com.all.language.translate.speech.text.ui.composable.favorite

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.all.language.translate.speech.text.data.model.History
import com.all.language.translate.speech.text.data.repo.HistoryRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel : ScreenModel {

    private val _favoriteList = MutableStateFlow<List<History>>(emptyList())
    val favoriteList = _favoriteList.asStateFlow()

    private val historyRepoImpl = HistoryRepoImpl()

    fun getAllHistory() {
        screenModelScope.launch {
            historyRepoImpl.getAllFavorites().collectLatest { list ->
                _favoriteList.update { list }
            }
        }
    }

    fun removeFavorite(history: History) = screenModelScope.launch { historyRepoImpl.removeFavorite(history) }

    fun removeAllFavorites() = screenModelScope.launch { historyRepoImpl.removeAllFavorites() }
}
