package com.all.language.translate.speech.text

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform