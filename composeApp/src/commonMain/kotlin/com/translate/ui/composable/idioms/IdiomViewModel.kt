package com.translate.ui.composable.idioms

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.translate.data.model.IdiomData
import com.translate.utils.Constant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlin.coroutines.coroutineContext

class IdiomViewModel : ScreenModel {

    private val json = Json { ignoreUnknownKeys = true }

    private val _idiomCategory = MutableStateFlow<List<String>>(emptyList())
    val idiomCategory = _idiomCategory.asStateFlow()

    private val _idiomData = MutableStateFlow<IdiomData.IdiomCategoryData?>(null)

    suspend fun initMain() {
        screenModelScope.launch {
            getIdiomCategory()
        }
    }

    fun getIdiomData(type: String = "Age") = flow {
        _idiomData.collect { idata ->
            var emitList: List<IdiomData.IdiomCategoryData.Idiom>
            idata?.let {
                emitList = emptyList()
                when (type) {
                    "Age" -> emitList = idata.age
                    "Animals" -> emitList = idata.animals
                    "Clothes" -> emitList = idata.clothes
                    "Colors" -> emitList = idata.colors
                    "Crime" -> emitList = idata.crime
                    "Death" ->emitList = idata.death
                    "Food" ->emitList = idata.food
                    "Furniture" ->emitList = idata.furniture
                    "General" ->emitList = idata.general
                    "Health" ->emitList = idata.health
                    "Home" ->emitList = idata.home
                    "Law" ->emitList = idata.law
                    "Life" ->emitList = idata.life
                    "Love" ->emitList = idata.love
                    "Men & Women" ->emitList = idata.menWomen
                    "Money" ->emitList = idata.money
                    "Music" ->emitList = idata.music
                    "Names" ->emitList = idata.names
                    "Nature" ->emitList = idata.nature
                    "Numbers" ->emitList = idata.numbers
                    "Parts of the body" ->emitList = idata.partsOfTheBody
                    "Relationship" ->emitList = idata.relationship
                    "Religion" ->emitList = idata.religion
                    "Sexuality" ->emitList = idata.sexuality
                    "Sport" ->emitList = idata.sport
                    "Science" ->emitList = idata.science
                    "Time" ->emitList = idata.time
                    "Travel" ->emitList = idata.travel
                    "War" ->emitList = idata.war
                    "Weather" ->emitList = idata.weather
                    "Work" ->emitList = idata.work
                    "Animal Idioms" ->emitList = idata.animalIdioms
                }
                emit(emitList)
            }
        }
    }.stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    private suspend fun getIdiomCategory() {
        val jsonString = withContext(coroutineContext) { Constant.idiomsJson }
        val jsonData =
            withContext(coroutineContext) { json.decodeFromString<IdiomData>(jsonString) }
        _idiomCategory.update { jsonData.idiomCategory }
        _idiomData.update { jsonData.idiomCategoryData }
    }

}