package com.translate

import android.content.Intent

actual fun shareText(text: String) {
    shareTextOnAndroid(text = text)
}

fun shareTextOnAndroid(text: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    MainActivity.context.startActivity(shareIntent)
}