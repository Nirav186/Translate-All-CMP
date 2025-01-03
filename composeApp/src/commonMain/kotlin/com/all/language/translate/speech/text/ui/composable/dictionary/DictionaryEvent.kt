package com.all.language.translate.speech.text.ui.composable.dictionary

sealed class DictionaryEvent {
    class OnQueryChange(val query:String): DictionaryEvent()
    data object OnSearch : DictionaryEvent()
    data object OnQueryClear: DictionaryEvent()
}