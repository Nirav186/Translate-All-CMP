package com.translate.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.translate.data.model.History
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Upsert
    suspend fun addHistory(history: History): Long

    @Delete
    suspend fun deleteHistory(history: History)

    @Update
    suspend fun updateHistory(history: History)

    @Query("select * from History")
    fun getAllHistory(): Flow<List<History>>

    @Query("DELETE FROM History")
    suspend fun deleteAllHistory(): Int

}