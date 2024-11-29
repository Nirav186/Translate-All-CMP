package com.translate.ui.composable.selection

import ExpandableSearchView
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
import androidx.compose.ui.unit.sp
import com.translate.data.model.Language
import com.translate.ui.composable.components.BackButton
import com.translate.utils.getDrawableByName
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
                if (isFrom) "from" else "to"
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
                    if (!expanded) {
                        Button(
                            modifier = Modifier
                                .padding(vertical = 2.dp)
                                .height(25.dp)
                                .width(48.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                                contentColor = MaterialTheme.colorScheme.primary,
                                disabledContainerColor = Color.Unspecified,
                                disabledContentColor = Color.Unspecified
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
                            Text(text = "Save", fontSize = 12.sp)
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
            verticalArrangement = Arrangement.spacedBy(4.dp)
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
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = 4.dp)
                .fillMaxWidth()
                .clickable(onClick = {
                    onClick()
                }),
            verticalAlignment = Alignment.CenterVertically
        ) {
            language.iconResId.getDrawableByName()?.let { drawableResource ->
                Image(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .size(32.dp)
                        .padding(4.dp),
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
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)) {
                        append(language.name)
                    }
                    if (language.popularityRank != 0) {
                        withStyle(style = SpanStyle(fontSize = 11.sp)) {
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
                    Icon(imageVector = Icons.AutoMirrored.Filled.VolumeUp, contentDescription = "voice")
                }
            }
        }
    }
}