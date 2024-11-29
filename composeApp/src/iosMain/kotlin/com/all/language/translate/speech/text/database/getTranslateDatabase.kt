package database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.all.language.translate.speech.text.data.local.TranslateDb
import platform.Foundation.NSHomeDirectory

fun getTranslateDatabase(): TranslateDb {
    val dbFile = NSHomeDirectory() + "/people.db"
    return Room.databaseBuilder<TranslateDb>(
        name = dbFile,
        factory = { TranslateDb::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}