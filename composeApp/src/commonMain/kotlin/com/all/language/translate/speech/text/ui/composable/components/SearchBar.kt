package com.all.language.translate.speech.text.ui.composable.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ExpandableSearchView(
    actionBarText: String,
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    expandedInitially: Boolean = false,
    tint: Color = MaterialTheme.colorScheme.onPrimary
) {

    Crossfade(targetState = expandedInitially, label = "") { isSearchFieldVisible ->
        when (isSearchFieldVisible) {
            true -> ExpandedSearchView(
                searchDisplay = searchDisplay,
                onSearchDisplayChanged = onSearchDisplayChanged,
                onSearchDisplayClosed = onSearchDisplayClosed,
                onExpandedChanged = onExpandedChanged,
                modifier = modifier,
                tint = MaterialTheme.colorScheme.primary
            )

            false -> CollapsedSearchView(
                onExpandedChanged = onExpandedChanged,
                modifier = modifier,
                tint = tint,
                actionBarText = actionBarText
            )
        }
    }
}

@Composable
fun SearchIcon(iconTint: Color) {
    Icon(
        imageVector = Icons.Filled.Search,
        contentDescription = "search icon",
        tint = iconTint
    )
}

@Composable
fun CollapsedSearchView(
    actionBarText: String,
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colorScheme.onPrimary,
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = actionBarText,
            fontSize = 16.ssp,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.ssp,
                fontWeight = FontWeight.Bold
            )
        )
        IconButton(onClick = { onExpandedChanged(true) }) {
            SearchIcon(iconTint = tint)
        }
    }
}

@Composable
fun ExpandedSearchView(
    searchDisplay: String,
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = Color.Blue,
) {
    val focusManager = LocalFocusManager.current

    val textFieldFocusRequester = remember { FocusRequester() }

    SideEffect {
        textFieldFocusRequester.requestFocus()
    }

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(searchDisplay, TextRange(searchDisplay.length)))
    }

    Row(
        modifier = modifier
            .offset(x = (-15).dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
            ),
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onSearchDisplayChanged(it.text)
            },
            leadingIcon = {
                IconButton(onClick = {
                    onExpandedChanged(false)
                    onSearchDisplayClosed()
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "back icon",
                        tint = tint
                    )
                }
            },
            trailingIcon = {
                SearchIcon(iconTint = tint)
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(textFieldFocusRequester),
            placeholder = {
                Text(text = "Search", color = tint)
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
    }
}

@Preview
@Composable
fun CollapsedSearchViewPreview() {
    androidx.compose.material.MaterialTheme {
        Surface(
            color = MaterialTheme.colorScheme.primary
        ) {
            ExpandableSearchView(
                actionBarText = "Translate",
                searchDisplay = "",
                onSearchDisplayChanged = {},
                onSearchDisplayClosed = {},
                onExpandedChanged = {}
            )
        }
    }
}

@Preview
@Composable
fun ExpandedSearchViewPreview() {
    androidx.compose.material.MaterialTheme {
        Surface(
            color = MaterialTheme.colorScheme.primary
        ) {
            ExpandableSearchView(
                actionBarText = "Translate",
                searchDisplay = "",
                onSearchDisplayChanged = {},
                expandedInitially = true,
                onSearchDisplayClosed = {},
                onExpandedChanged = {}
            )
        }
    }
}