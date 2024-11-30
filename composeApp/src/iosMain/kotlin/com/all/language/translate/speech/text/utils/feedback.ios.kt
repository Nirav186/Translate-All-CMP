package com.all.language.translate.speech.text.utils

import platform.Foundation.*
import platform.MessageUI.*
import platform.UIKit.*

actual fun sendFeedback() {
    // Create an instance of the view controller
    val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
        ?: error("No root view controller found")

    // Ensure mail services are available
    if (!MFMailComposeViewController.canSendMail()) {
        println("Mail services are not available. Please configure an email account.")
        return
    }

    val mailComposeVC = MFMailComposeViewController().apply {
        setMailComposeDelegate(rootViewController as? MFMailComposeViewControllerDelegate)
        setToRecipients(listOf("manthanjivani.apps@gmail.com"))
        setSubject("Feedback")
        setMessageBody("Please write your feedback here.", isHTML = false)
    }

    rootViewController.presentViewController(mailComposeVC, animated = true, completion = null)
}