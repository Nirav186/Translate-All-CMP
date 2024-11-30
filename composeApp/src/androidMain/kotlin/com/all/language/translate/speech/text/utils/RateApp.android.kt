package com.all.language.translate.speech.text.utils

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import com.all.language.translate.speech.text.MainActivity.Companion.context

actual fun rateApp() {
    val packageName = context.packageName
    try {
        val marketIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("market://details?id=$packageName")
        )
        context.startActivity(marketIntent)
    } catch (e: ActivityNotFoundException) {
        val playStoreIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
        )
        context.startActivity(playStoreIntent)
    }
}