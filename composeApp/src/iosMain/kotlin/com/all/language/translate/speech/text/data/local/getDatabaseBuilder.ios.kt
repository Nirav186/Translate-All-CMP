package com.all.language.translate.speech.text.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask
actual class GetDatabaseBuilder {
    actual fun builder(): RoomDatabase.Builder<TranslateDb> {
        val dbFile = documentDirectory() + "/translate.db"
        return Room.databaseBuilder<TranslateDb>(
            name = dbFile,
        )
            .setDriver(BundledSQLiteDriver())
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}
