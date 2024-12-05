package com.all.language.translate.speech.text.data.repo

import com.all.language.translate.speech.text.data.local.HistoryDao
import com.all.language.translate.speech.text.data.model.History
import com.all.language.translate.speech.text.utils.Constant
import kotlinx.coroutines.flow.Flow

class HistoryRepoImpl(private val historyDao: HistoryDao = Constant.historyDao) {

    suspend fun addHistory(history: History): Long {
        return historyDao.addHistory(history)
    }

    suspend fun deleteHistory(history: History) = historyDao.deleteHistory(history)

    suspend fun updateHistory(history: History) = historyDao.updateHistory(history)

    fun getAllHistory(): Flow<List<History>> = historyDao.getAllHistory()

    suspend fun deleteAllHistory(): Int = historyDao.deleteAllHistory()

    suspend fun removeAllFavorites() = historyDao.removeAllFavorites()

    suspend fun removeFavorite(history: History) = historyDao.removeFavorite(history.id)

    fun getAllFavorites() = historyDao.getAllFavorites()
}