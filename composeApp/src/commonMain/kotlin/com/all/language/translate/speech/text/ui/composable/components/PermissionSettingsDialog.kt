package com.all.language.translate.speech.text.ui.composable.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.W800
import androidx.compose.ui.text.style.TextAlign
import dev.icerock.moko.permissions.PermissionState
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionSettingsDialog(
    title: String,
    message: String,
    onDismissRequest: () -> Unit,
    onSettingsClick: () -> Unit,
    permissionState: PermissionState
) {
    BasicAlertDialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.sdp, end = 12.sdp)
                    .fillMaxWidth(),
            ) {
                Spacer(modifier = Modifier.height(14.sdp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    fontWeight = W800,
                    fontSize = 16.ssp
                )
                Spacer(modifier = Modifier.height(8.sdp))
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    color = Color.Gray,
                    fontSize = 10.ssp
                )
                Spacer(modifier = Modifier.height(8.sdp))
                Row(
                    modifier = Modifier.padding(bottom = 4.sdp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismissRequest,
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Gray
                        ),
                    ) {
                        Text("Cancel", fontSize = 12.ssp)
                    }
                    TextButton(onClick = onSettingsClick) {
                        Text(if(permissionState == PermissionState.Denied) "Allow Permission" else "Open Settings", fontSize = 12.ssp)
                    }
                }
            }
        }
    }
}
