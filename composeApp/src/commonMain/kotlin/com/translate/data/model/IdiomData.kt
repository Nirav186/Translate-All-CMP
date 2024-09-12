package com.translate.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class IdiomData(
    @SerialName("data") val idiomCategoryData: IdiomCategoryData,
    @SerialName("keys") val idiomCategory: List<String>
) {
    @Serializable
    data class IdiomCategoryData(

        @SerialName("Age")
        val age: List<Idiom>,
        @SerialName("Animal Idioms")
        val animalIdioms: List<Idiom>,
        @SerialName("Animals")
        val animals: List<Idiom>,
        @SerialName("Clothes")
        val clothes: List<Idiom>,
        @SerialName("Colors")
        val colors: List<Idiom>,
        @SerialName("Crime")
        val crime: List<Idiom>,
        @SerialName("Death")
        val death: List<Idiom>,
        @SerialName("Food")
        val food: List<Idiom>,
        @SerialName("Furniture")
        val furniture: List<Idiom>,
        @SerialName("General")
        val general: List<Idiom>,
        @SerialName("Health")
        val health: List<Idiom>,
        @SerialName("Home")
        val home: List<Idiom>,
        @SerialName("Law")
        val law: List<Idiom>,
        @SerialName("Life")
        val life: List<Idiom>,
        @SerialName("Love")
        val love: List<Idiom>,
        @SerialName("Men & Women")
        val menWomen: List<Idiom>,
        @SerialName("Money")
        val money: List<Idiom>,
        @SerialName("Music")
        val music: List<Idiom>,
        @SerialName("Names")
        val names: List<Idiom>,
        @SerialName("Nature")
        val nature: List<Idiom>,
        @SerialName("Numbers")
        val numbers: List<Idiom>,
        @SerialName("Parts of the body")
        val partsOfTheBody: List<Idiom>,
        @SerialName("Relationship")
        val relationship: List<Idiom>,
        @SerialName("Religion")
        val religion: List<Idiom>,
        @SerialName("Science")
        val science: List<Idiom>,
        @SerialName("Sexuality")
        val sexuality: List<Idiom>,
        @SerialName("Sport")
        val sport: List<Idiom>,
        @SerialName("Time")
        val time: List<Idiom>,
        @SerialName("Travel")
        val travel: List<Idiom>,
        @SerialName("War")
        val war: List<Idiom>,
        @SerialName("Weather")
        val weather: List<Idiom>,
        @SerialName("Work")
        val work: List<Idiom>,
    ) {
        @Serializable
        data class Idiom(
            @SerialName("Idiom") val idiom: String,
            @SerialName("Define") val define: String,
            @SerialName("Ex") val ex: String
        )
    }
}