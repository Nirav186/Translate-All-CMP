package com.all.language.translate.speech.text.ui.composable.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.all.language.translate.speech.text.data.model.Language
import com.all.language.translate.speech.text.ui.composable.dashboardOne.DashBoardViewModel
import multiplatform.network.cmptoast.showToast
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.painterResource
import translate.composeapp.generated.resources.Res
import translate.composeapp.generated.resources.ic_arrow_both_side

@Composable
fun RotatingSwapIcon(
    dashBoardViewModel: DashBoardViewModel,
    selectedFromLang: Language,
    selectedToLang: Language
) {
    var isRotated by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isRotated) 180f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    Icon(
        modifier = Modifier
            .padding(horizontal = 8.sdp)
            .clickable(
                onClick = {
                    dashBoardViewModel.swapSelectedLanguage(
                        selectedFromLang,
                        selectedToLang
                    ) { success ->
                        if (!success) {
                            showToast("Can not swap these languages try another language")
                        } else {
                            isRotated = !isRotated
                        }
                    }
                }
            )
            .rotate(rotation),
        painter = painterResource(Res.drawable.ic_arrow_both_side),
        contentDescription = null
    )
}