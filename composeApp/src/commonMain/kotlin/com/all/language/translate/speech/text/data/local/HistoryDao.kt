package com.all.language.translate.speech.text.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.all.language.translate.speech.text.data.model.History
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

    @Query("UPDATE History SET isFavorite = false")
    suspend fun removeAllFavorites()

    @Query("UPDATE History SET isFavorite = false WHERE id = :id")
    suspend fun removeFavorite(id: Int): Int

    @Query("SELECT * FROM History WHERE isFavorite = true")
    fun getAllFavorites(): Flow<List<History>>
}