package com.all.language.translate.speech.text.ui.composable.quotes

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.all.language.translate.speech.text.data.model.Quote
import com.all.language.translate.speech.text.utils.getQuotesJson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlin.coroutines.coroutineContext

class QuotesViewModel : ScreenModel {

    private val json = Json { ignoreUnknownKeys = true }

    var quoteList = MutableStateFlow<List<Quote>>(emptyList())
        private set

    init {
        screenModelScope.launch {
            getQuotes()
        }
    }

    private suspend fun getQuotes() {
        val jsonString = withContext(coroutineContext) { getQuotesJson() }
        val jsonData =
            withContext(coroutineContext) { json.decodeFromString<List<Quote>>(jsonString) }
        quoteList.update { jsonData }
    }


    fun getQuoteByAuthor(author: String) = flow {
        quoteList.value.firstOrNull { it.name == author }?.let {
            emit(it)
        }
    }.stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000), null)


}