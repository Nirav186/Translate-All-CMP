package com.translate.ui.composable.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

@Composable
fun DictionaryResponseCard(
    modifier: Modifier = Modifier,
    word: String,
    phonetic: String,
    meanings: List<String>
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.sdp),
        shadowElevation = 2.sdp,
        tonalElevation = 0.dp,
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(16.sdp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.sdp)
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dictionary",
                    style = TextStyle(
                        fontSize = 14.ssp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.width(8.sdp))
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp),
                    color = Color.Gray
                )
            }

            // Word
            Text(
                text = word,
                style = TextStyle(
                    fontSize = 18.ssp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

            // Phonetic
            if (phonetic.isNotEmpty()) {
                Text(
                    text = "Phonetic: $phonetic",
                    style = TextStyle(
                        fontSize = 14.ssp,
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray
                    )
                )
            }


            // Meanings
            Text(
                text = "Meanings:",
                style = TextStyle(
                    fontSize = 16.ssp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            meanings.forEach { meaning ->
                Text(
                    text = "- $meaning",
                    style = TextStyle(
                        fontSize = 14.ssp,
                        color = Color.DarkGray
                    )
                )
            }
        }
    }
}