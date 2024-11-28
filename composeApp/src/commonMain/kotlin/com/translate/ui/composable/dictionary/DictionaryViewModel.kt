package com.translate.ui.composable.dictionary

import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel

class DictionaryViewModel() : ScreenModel {

    val searchQuery = mutableStateOf("")

    fun onDictionaryEvent(state: DictionaryEvent) {
        when (state) {
            is DictionaryEvent.OnQueryChange -> {
                searchQuery.value = state.query
            }

            DictionaryEvent.OnQueryClear -> {
                searchQuery.value = ""
            }

            is DictionaryEvent.OnSearch -> {

            }
        }
    }
}