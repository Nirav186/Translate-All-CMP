package com.all.language.translate.speech.text.data.local

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<TranslateDb> {
    override fun initialize(): TranslateDb
}