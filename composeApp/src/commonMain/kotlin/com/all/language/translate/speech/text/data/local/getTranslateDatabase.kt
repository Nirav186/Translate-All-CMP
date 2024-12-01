package com.all.language.translate.speech.text.data.local

import androidx.room.RoomDatabase

expect class getTranslateDatabase  {
    fun builder() :  RoomDatabase.Builder<TranslateDb>
}