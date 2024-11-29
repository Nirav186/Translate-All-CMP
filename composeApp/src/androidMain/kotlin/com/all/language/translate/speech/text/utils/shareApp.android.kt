package com.all.language.translate.speech.text.utils

import android.content.Intent
import com.all.language.translate.speech.text.MainActivity.Companion.context

actual fun shareApp() {
    val shareText = "📺 Check out this Cool Language Translator App 😃👇" +
            "http://play.google.com/store/apps/details?id=${context.packageName}"

    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }

    context.startActivity(Intent.createChooser(shareIntent, "Share this App"))
}