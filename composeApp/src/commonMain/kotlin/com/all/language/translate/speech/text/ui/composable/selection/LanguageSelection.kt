package com.all.language.translate.speech.text.ui.composable.selection

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.all.language.translate.speech.text.data.model.Language
import com.all.language.translate.speech.text.ui.composable.components.BackButton
import com.all.language.translate.speech.text.ui.composable.components.ExpandableSearchView
import com.all.language.translate.speech.text.utils.getDrawableByName
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelection(
    languageSelectionViewModel: LanguageSelectionViewModel,
    isFrom: Boolean,
    onSaveClick: () -> Unit,
) {
    val titleText by remember {
        mutableStateOf(
            "Translate ${
                if (isFrom) "From" else "To"
            }"
        )
    }
    val languages by languageSelectionViewModel.languageList.collectAsState(initial = emptyList())
    val searchQuery by languageSelectionViewModel.searchText.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf<Language?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.surface,
                    actionIconContentColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.surface
                ),
                actions = {
                    if (!expanded && selectedLanguage != null) {
                        Button(
                            modifier = Modifier
                                .padding(vertical = 2.dp)
                                .height(28.dp)
                                .width(60.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                contentColor = MaterialTheme.colorScheme.primary,
                                disabledContainerColor = Color.Unspecified,
                                disabledContentColor = MaterialTheme.colorScheme.surface
                            ),
                            contentPadding = PaddingValues(0.dp),
                            onClick = {
                                languageSelectionViewModel.saveLanguageIndex(
                                    selectedLanguage,
                                    isFrom
                                )
                                onSaveClick()
                            },
                            enabled = selectedLanguage != null
                        ) {
                            Text(text = "Save", fontSize = 12.ssp)
                        }
                    }
                },
                navigationIcon = {
                    if (!expanded) {
                        BackButton {
                            onSaveClick()
                        }
                    }
                },
                title = {
                    ExpandableSearchView(
                        actionBarText = titleText,
                        searchDisplay = searchQuery,
                        expandedInitially = expanded,
                        onSearchDisplayChanged = { q ->
                            languageSelectionViewModel.updateSearchQuery(q)
                        },
                        onExpandedChanged = { b -> expanded = b },
                        onSearchDisplayClosed = {
                            languageSelectionViewModel.updateSearchQuery("")
                        }
                    )
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(2.sdp)
        ) {
            items(
                languages.sortedBy { itm -> itm.popularityRank },
                key = { itm -> itm.popularityRank }) { lang ->
                if (!isFrom && lang.popularityRank == 0) {
                    return@items
                }
                LanguageCard(
                    language = lang,
                    isSelected = selectedLanguage == lang,
                    onClick = {
                        selectedLanguage = lang
                        languageSelectionViewModel.saveLanguageIndex(
                            selectedLanguage,
                            isFrom
                        )
                        onSaveClick()
                    }
                )
            }
        }
    }
}

@Composable
fun LanguageCard(
    modifier: Modifier = Modifier,
    language: Language,
    onClick: () -> Unit,
    isSelected: Boolean = false
) {
    Card(
        modifier = modifier
            .padding(horizontal = 6.sdp, vertical = 4.sdp),
        elevation = CardDefaults.cardElevation(4.sdp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.sdp)
        ),
        shape = RoundedCornerShape(8.sdp)
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(horizontal = 8.sdp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            language.iconResId.getDrawableByName()?.let { drawableResource ->
                Image(
                    modifier = Modifier
                        .padding(vertical = 4.sdp)
                        .size(32.sdp)
                        .padding(4.sdp),
                    painter = painterResource(drawableResource),
                    contentDescription = null
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.ssp)) {
                        append(language.name)
                    }
                    if (language.popularityRank != 0) {
                        withStyle(style = SpanStyle(fontSize = 11.ssp)) {
                            append("  ")
                            append(language.nativeName)
                        }
                    }
                })
                if (isSelected) {
                    Icon(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        imageVector = Icons.Filled.Check, contentDescription = "check"
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(20.dp),
                contentAlignment = Alignment.Center
            ) {
                if (language.isActive && language.popularityRank != 0) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "voice"
                    )
                }
            }
        }
    }
}