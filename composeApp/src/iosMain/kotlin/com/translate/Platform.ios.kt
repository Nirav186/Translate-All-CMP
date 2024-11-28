package com.translate

import platform.UIKit.UIDevice
import androidx.compose.ui.text.font.Font
import platform.UIKit.UIFont

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()
actual fun getMontRFont(): Font {
    val font = UIFont("Montserrat-Regular", 17.0)
    return Font(font)
}

actual fun getMontBFont(): Font {
    val font = UIFont("Montserrat-Bold", 17.0)
    return Font(font)
}

actual fun getMontSBFont(): Font {
    val font = UIFont("Montserrat-SemiBold", 17.0)
    return Font(font)
}

actual fun getRobotoFont(): Font {
    val font = UIFont("Roboto-Medium", 17.0)
    return Font(font)
}