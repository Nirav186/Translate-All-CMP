package com.all.language.translate.speech.text.utils

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun rateApp() {
    val appStoreID = "YOUR_APP_ID"
    val appStoreUrl = "https://apps.apple.com/us/app/gps-camera-map-photo-timestamp/id$appStoreID"

    val nsUrl = NSURL(string = appStoreUrl)
    if (nsUrl != null) {
        UIApplication.sharedApplication.openURL(nsUrl)
    } else {
        println("Failed to create App Store review URL.")
    }
}
