package com.translate

import androidx.compose.ui.text.font.Font

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getMontRFont(): Font
expect fun getMontBFont(): Font
expect fun getMontSBFont(): Font
expect fun getRobotoFont(): Font