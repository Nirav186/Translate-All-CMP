package com.translate

import android.os.Build
import androidx.compose.ui.text.font.Font

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getMontRFont(): Font = Font(R.font.montserratregular)

actual fun getMontBFont(): Font = Font(R.font.montserratbold)

actual fun getMontSBFont(): Font = Font(R.font.montserratsemibold)

actual fun getRobotoFont(): Font = Font(R.font.roboto_medium_numbers)