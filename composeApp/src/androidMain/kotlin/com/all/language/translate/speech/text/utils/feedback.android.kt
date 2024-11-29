package com.all.language.translate.speech.text.utils


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.all.language.translate.speech.text.MainActivity.Companion.context

actual fun sendFeedback() {
    val emailAddress = "manthanjivani.apps@gmail.com"
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        setDataAndType(Uri.parse("mailto:"),"message/rfc822")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
        putExtra(Intent.EXTRA_SUBJECT, "Feedback")
        putExtra(Intent.EXTRA_TEXT, "")
    }

    try {
        context.startActivity(emailIntent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "No email app found on this device", Toast.LENGTH_SHORT).show()
    }
}
