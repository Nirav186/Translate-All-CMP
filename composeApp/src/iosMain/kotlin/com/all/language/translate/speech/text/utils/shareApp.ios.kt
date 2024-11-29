package com.all.language.translate.speech.text.utils

import platform.Foundation.NSURL
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController

actual fun shareApp() {
    // todo put app id here
    val appStoreID = "YOUR_APP_ID"
    val appStoreUrl = "https://apps.apple.com/us/app/gps-camera-map-photo-timestamp/id$appStoreID"


    val shareText = "Check out this app!"
    val appURL = NSURL(string = appStoreUrl)

    if (appURL != null) {
        val activityItems: List<Any> = listOf(shareText, appURL)

        val activityVC = UIActivityViewController(activityItems = activityItems.toTypedArray(), applicationActivities = null)

        val currentViewController = UIApplication.sharedApplication.keyWindow?.rootViewController as? UIViewController
        currentViewController?.presentViewController(activityVC, animated = true, completion = null)
    }
}

