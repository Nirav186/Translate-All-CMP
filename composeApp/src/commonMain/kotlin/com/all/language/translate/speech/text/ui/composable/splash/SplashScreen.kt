package com.all.language.translate.speech.text.ui.composable.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.all.language.translate.speech.text.ui.composable.dashboardOne.DashBoardScreen
import kotlinx.coroutines.delay
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import translate.composeapp.generated.resources.Res
import translate.composeapp.generated.resources.app_name
import translate.composeapp.generated.resources.bg_splash
import translate.composeapp.generated.resources.ic_splash_logo

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            delay(1000L)
            navigator.push(DashBoardScreen())
//            navHostController.navigate(Screens.OnBoard) {
//                popUpTo<Screens.SplashScreen> { inclusive = true }
//            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(Res.drawable.bg_splash),
                contentDescription = null
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(140.sdp),
                    painter = painterResource(Res.drawable.ic_splash_logo),
                    contentDescription = "Splash Logo"
                )
                Spacer(modifier = Modifier.height(10.sdp))
                Text(
                    text = stringResource(Res.string.app_name),
                    color = Color.White,
                    fontSize = 24.ssp,
                )
            }
        }
    }
}