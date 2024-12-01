package com.all.language.translate.speech.text.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

actual class getTranslateDatabase(
    private val appContext: Context
) {
    actual fun builder(): RoomDatabase.Builder<TranslateDb> {
        val appContext = appContext.applicationContext
        val dbFile = appContext.getDatabasePath("translate.db")

        return Room.databaseBuilder<TranslateDb>(
            context = appContext,
            name = dbFile.absolutePath
        ).setDriver(BundledSQLiteDriver())
    }
}