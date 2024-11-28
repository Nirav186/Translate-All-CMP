package com.translate

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import database.getTranslateDatabase

fun MainViewController() = ComposeUIViewController {
    val dao = remember {
        getTranslateDatabase().getHistoryDao()
    }
    App(dao)
}