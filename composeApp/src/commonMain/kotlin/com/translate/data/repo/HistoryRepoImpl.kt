package com.translate.data.repo

import com.translate.data.local.HistoryDao
import com.translate.data.model.History
import com.translate.utils.Constant
import kotlinx.coroutines.flow.Flow

class HistoryRepoImpl(private val historyDao: HistoryDao = Constant.historyDao) {

    suspend fun addHistory(history: History): Long {
        return historyDao.addHistory(history)
    }

    suspend fun deleteHistory(history: History) = historyDao.deleteHistory(history)

    suspend fun updateHistory(history: History) = historyDao.updateHistory(history)

    fun getAllHistory(): Flow<List<History>> = historyDao.getAllHistory()

    suspend fun deleteAllHistory(): Int = historyDao.deleteAllHistory()
}