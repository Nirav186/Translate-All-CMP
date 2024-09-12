package com.translate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.translate.data.model.History

@Database(entities = [History::class], version = 1)
//@TypeConverters(Converters::class)
abstract class TranslateDb : RoomDatabase() {
    abstract fun getHistoryDao(): HistoryDao
}