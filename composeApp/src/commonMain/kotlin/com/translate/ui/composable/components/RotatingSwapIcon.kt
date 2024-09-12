package com.translate.ui.composable.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SyncAlt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import com.translate.data.model.Language
import com.translate.ui.composable.dashboardOne.DashBoardViewModel
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun RotatingSwapIcon(
    dashBoardViewModel: DashBoardViewModel,
    selectedFromLang: Language,
    selectedToLang: Language
) {
    var isRotated by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isRotated) 180f else 0f,
        animationSpec = tween(durationMillis = 200)
    )

    Icon(
        modifier = Modifier
            .padding(horizontal = 12.sdp)
            .clickable(
                onClick = {
                    isRotated = !isRotated
                    dashBoardViewModel.swapSelectedLanguage(selectedFromLang, selectedToLang)
                }
            )
            .rotate(rotation),
        imageVector = Icons.Outlined.SyncAlt,
        contentDescription = null
    )
}