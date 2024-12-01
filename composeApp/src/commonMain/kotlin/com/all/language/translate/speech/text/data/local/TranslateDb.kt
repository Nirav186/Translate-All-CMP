package com.all.language.translate.speech.text.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.all.language.translate.speech.text.data.model.History

@Database(entities = [History::class], version = 1, exportSchema = false)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class TranslateDb : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
}