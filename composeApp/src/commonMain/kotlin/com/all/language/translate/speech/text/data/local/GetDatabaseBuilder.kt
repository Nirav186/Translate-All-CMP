package com.all.language.translate.speech.text.data.local

import androidx.room.RoomDatabase

expect class GetDatabaseBuilder {
   fun builder() : RoomDatabase.Builder<TranslateDb>
}