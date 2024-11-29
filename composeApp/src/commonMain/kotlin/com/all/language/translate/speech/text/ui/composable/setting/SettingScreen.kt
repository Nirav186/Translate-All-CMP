package com.all.language.translate.speech.text.ui.composable.setting

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import cafe.adriel.voyager.core.screen.Screen
import com.all.language.translate.speech.text.utils.openMoreApps
import com.all.language.translate.speech.text.utils.openPrivacyPolicy
import com.all.language.translate.speech.text.utils.rateApp
import com.all.language.translate.speech.text.utils.sendFeedback
import com.all.language.translate.speech.text.utils.shareApp
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import translate.composeapp.generated.resources.Res
import translate.composeapp.generated.resources.ic_check_update
import translate.composeapp.generated.resources.ic_feedback
import translate.composeapp.generated.resources.ic_moreapps
import translate.composeapp.generated.resources.ic_policy
import translate.composeapp.generated.resources.ic_rateApp
import translate.composeapp.generated.resources.ic_share
import translate.composeapp.generated.resources.ic_splash_logo

class SettingScreen : Screen {
    @Composable
    override fun Content() {
        SettingScreenContent()
    }
}

@Composable
fun SettingScreenContent() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 20.sdp, horizontal = 10.sdp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.sdp)
    ) {

        item {
            Image(
                modifier = Modifier.padding(top = 30.sdp, bottom = 20.sdp).size(80.sdp),
                painter = painterResource(Res.drawable.ic_splash_logo),
                contentDescription = "Logo"
            )
        }

        item {
            Text(
                modifier = Modifier.padding(bottom = 20.sdp),
                text = "Language Translator",
                style = TextStyle(
                    fontSize = 20.ssp,
                    fontWeight = W600
                )
            )
        }

        item {
            SettingItem(
                icon = Res.drawable.ic_check_update,
                name = "Check for Updates",
                onClick = { rateApp() }
            )
        }
        item {
            SettingItem(
                icon = Res.drawable.ic_rateApp,
                name = "Rate Us",
                onClick = { rateApp() }
            )
        }
        item {
            SettingItem(
                icon = Res.drawable.ic_moreapps,
                name = "More Apps",
                onClick = { openMoreApps() }
            )
        }
        item {
            SettingItem(
                icon = Res.drawable.ic_share,
                name = "Share App",
                onClick = { shareApp() }
            )
        }
        item {
            SettingItem(
                icon = Res.drawable.ic_feedback,
                name = "Feedback",
                onClick = { sendFeedback() }
            )
        }
        item {
            SettingItem(
                icon = Res.drawable.ic_policy,
                name = "Privacy Policy",
                onClick = { openPrivacyPolicy() }
            )
        }
    }
}

@Composable
fun SettingItem(
    icon: DrawableResource,
    name: String,
    onClick: () -> Unit
) {
    Surface(
        border = BorderStroke(width = 1.sdp, Color(0xFFe8e8e8)),
        shape = RoundedCornerShape(8.sdp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(8.sdp),
            verticalAlignment = CenterVertically
        ) {
            Image(
                modifier = Modifier.size(28.sdp),
                painter = painterResource(icon),
                contentDescription = name
            )
            Text(
                modifier = Modifier.padding(start = 12.sdp).weight(1f),
                text = name,
                style = TextStyle(
                    fontSize = 14.ssp,
                    fontWeight = W500
                )
            )
        }
    }

}