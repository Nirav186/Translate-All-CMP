package com.translate.ui.composable.dictionary

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.translate.data.model.DictionaryResponseItem
import com.translate.utils.Constant
import com.translate.utils.CustomError
import com.translate.utils.Result
import com.translate.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DictionaryViewModel() : ScreenModel {

    val searchQuery = MutableStateFlow("")

    val dictionaryResponseState =
        MutableStateFlow<Result<DictionaryResponseItem?, CustomError<String>>>(Result.Idle)

    fun onDictionaryEvent(state: DictionaryEvent) {
        when (state) {
            is DictionaryEvent.OnQueryChange -> {
                searchQuery.update { state.query }
            }

            DictionaryEvent.OnQueryClear -> {
                searchQuery.update { "" }
            }

            is DictionaryEvent.OnSearch -> {
                val query = searchQuery.value
                screenModelScope.launch {
                    dictionaryResponseState.value = Result.Idle
                    if (query.isEmpty()) {
                        dictionaryResponseState.value = Result.Error(CustomError("Empty query"))
                    } else {
                        val response = Constant.dictionaryClient.findDictionaryForWord(query)
                        response.onSuccess {
                            dictionaryResponseState.value = Result.Success(it[0])
                        }
                    }
                }
            }
        }
    }
}