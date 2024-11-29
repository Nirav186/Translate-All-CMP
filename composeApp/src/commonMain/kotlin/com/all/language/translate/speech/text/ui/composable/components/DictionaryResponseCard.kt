package com.all.language.translate.speech.text.ui.composable.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.all.language.translate.speech.text.data.model.DictionaryResponseItem
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

@Composable
fun DictionaryResponseCard(
    modifier: Modifier = Modifier,
    word: String,
    phonetic: String,
) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.sdp),
        shadowElevation = 2.sdp,
        tonalElevation = 0.dp,
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(8.sdp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.sdp)
        ) {

            // Word
            Text(
                text = word,
                style = TextStyle(
                    fontSize = 16.ssp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

            // Phonetic
            if (phonetic.isNotEmpty()) {
                TypewriterText(
                    text = "Phonetic: $phonetic",
                    style = TextStyle(
                        fontSize = 12.ssp,
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray
                    )
                )
            }
        }
    }
}

/*

@Composable
fun MeaningItem(meaning: DictionaryResponseItem.Meaning?) {
    Column {
        meaning?.partOfSpeech?.let { partOfSpeech ->
            TypewriterText(
                text = "Part of Speech: $partOfSpeech",
            )
        }

        meaning?.definitions?.forEach { definition ->
            definition?.let {
                it.definition?.let { def ->
                    TypewriterText(
                        text = "Definition: $def",
                    )
                }

                it.example?.let { example ->
                    TypewriterText(
                        text = "Example: $example",
                    )
                }

                if (!it.synonyms.isNullOrEmpty()) {
                    TypewriterText(
                        text = "Synonyms: ${it.synonyms.joinToString(", ")}",
                    )
                }

                if (!it.antonyms.isNullOrEmpty()) {
                    TypewriterText(
                        text = "Antonyms: ${it.antonyms.joinToString(", ")}",
                    )
                }
            }
        }
    }
}
*/
@Composable
fun MeaningItems(
    modifier: Modifier = Modifier,
    meaning: DictionaryResponseItem.Meaning?
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.sdp),
        shadowElevation = 2.sdp,
        tonalElevation = 0.dp,
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(8.sdp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.sdp)
        ) {

            meaning?.partOfSpeech?.let { partOfSpeech ->
                TypewriterText(
                    text = "$partOfSpeech",
                    style = TextStyle(fontStyle = FontStyle.Italic, color = Color.Gray, fontSize = 12.ssp)
                )
            }
            Text(
                text =
                buildAnnotatedString {

                    meaning?.definitions?.forEach { definition ->
                        definition?.let {
                            it.definition?.let { def ->
                                append("\"")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                                    append(def)
                                }
                                append("\"\n")
                            }

                            it.example?.let { example ->
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Ex: ")
                                }
                                append(example)
                                append("\n\n")
                            }

                            if (!it.synonyms.isNullOrEmpty()) {
                                append("Synonyms: \n")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                                    append(it.synonyms.joinToString(", "))
                                }
                                append("\n")
                            }

                            if (!it.antonyms.isNullOrEmpty()) {
                                append("Antonyms: \n")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                                    append(it.antonyms.joinToString(", "))
                                }
                                append("\n")
                            }
                        }
                    }
                }
            )
        }
    }
}
