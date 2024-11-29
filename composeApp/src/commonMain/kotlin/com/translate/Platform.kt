package com.translate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform