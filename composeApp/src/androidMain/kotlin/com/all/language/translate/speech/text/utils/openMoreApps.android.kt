package com.all.language.translate.speech.text.utils

import android.content.Intent
import android.net.Uri
import com.all.language.translate.speech.text.MainActivity.Companion.context

actual fun openMoreApps() {
    val url = "https://play.google.com/store/apps/developer?id=UniqueApp+Technologies"
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    context.startActivity(intent)
}