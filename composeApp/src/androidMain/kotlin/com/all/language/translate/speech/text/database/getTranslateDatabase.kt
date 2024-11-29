package com.all.language.translate.speech.text.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.all.language.translate.speech.text.data.local.TranslateDb

fun getTranslateDatabase(context: Context): TranslateDb {
    val dbFile = context.getDatabasePath("translate.db")
    return Room.databaseBuilder<TranslateDb>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}