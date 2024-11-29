package com.all.language.translate.speech.text.utils

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun rateApp() {

    // todo put app id here
    val appStoreID = "YOUR_APP_ID"
    val appStoreUrl = "https://apps.apple.com/us/app/gps-camera-map-photo-timestamp/id$appStoreID"

    val nsUrl = NSURL(string = appStoreUrl)
    if (nsUrl != null) {
        UIApplication.shared.open(nsUrl, mapOf()) { success ->
            if (!success) {
                print("Failed to open the App Store review page.")
            }
        }
    } else {
        println("Failed to create App Store review URL.")
    }
}
