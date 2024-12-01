package com.all.language.translate.speech.text.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory


actual class getTranslateDatabase() {
    actual fun builder(): RoomDatabase.Builder<TranslateDb> {
        val dbFile = NSHomeDirectory() + "/translate.db"
        return Room.databaseBuilder<TranslateDb>(
            name = dbFile,
            factory = { TranslateDb::class.instantiateImpl() }
        )
            .setDriver(BundledSQLiteDriver())
    }
}