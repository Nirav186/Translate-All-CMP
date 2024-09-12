package com.translate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val id: Int = 0,
    val code: String,
    val name: String,
    val nativeName: String,
    val iconResId: String,
    val isActive: Boolean,
    val popularityRank: Int
) {
    constructor(
        iconResId: String,
        name: String,
        nativeName: String,
        code: String,
        isActive: Boolean,
        popularityRank: Int
    ) : this(
        id = 0,
        iconResId = iconResId,
        name = name,
        nativeName = nativeName,
        code = code,
        isActive = isActive,
        popularityRank = popularityRank
    )
}