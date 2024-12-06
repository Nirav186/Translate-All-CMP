package com.all.language.translate.speech.text.utils
// iOS implementation
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openPrivacyPolicy() {
    val url = "https://sites.google.com/view/uplineapps/privacy-policy"
    val nsUrl = NSURL(string = url)
    if (nsUrl != null && UIApplication.sharedApplication.canOpenURL(nsUrl)) {
        UIApplication.sharedApplication.openURL(nsUrl)
    }
}
