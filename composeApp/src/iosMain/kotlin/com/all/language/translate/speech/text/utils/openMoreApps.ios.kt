package com.all.language.translate.speech.text.utils

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openMoreApps() {
    val urlString = "https://apps.apple.com/us/developer/deep-jerajbhai-davara/id1683267123"
    val url = NSURL(string = urlString)

    if (url != null && UIApplication.sharedApplication.canOpenURL(url)) {
        UIApplication.sharedApplication.openURL(url)
    } else {
        println("Failed to open the App Store developer page.")
    }
}

