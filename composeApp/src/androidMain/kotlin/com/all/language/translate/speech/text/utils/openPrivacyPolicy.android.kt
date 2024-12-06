package com.all.language.translate.speech.text.utils

import android.content.Intent
import android.net.Uri
import com.all.language.translate.speech.text.MainActivity.Companion.context

actual fun openPrivacyPolicy() {
    val intent =
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://sites.google.com/view/uplineapps/privacy-policy")
        )
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}