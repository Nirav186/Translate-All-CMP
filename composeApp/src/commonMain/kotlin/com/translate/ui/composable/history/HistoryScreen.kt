package com.translate.ui.composable.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.translate.data.model.History
import com.translate.ui.composable.components.EmptyScreen
import com.translate.ui.composable.translate.TranslateScreen
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

class HistoryScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val historyViewModel = rememberScreenModel { HistoryViewModel() }
        LaunchedEffect(true) {
            historyViewModel.getAllHistory()
        }
        HistoryScreenContent(
            historyViewModel = historyViewModel,
            goBack = {
                navigator.pop()
            },
            navigateTranslateScreen = {
                navigator.push(TranslateScreen(it))
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreenContent(
    historyViewModel: HistoryViewModel,
    navigateTranslateScreen: (History) -> Unit,
    goBack: () -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }
    val historyList by historyViewModel.historyList.collectAsState()

    if (showDialog) {
        AlertDialog(
            properties = DialogProperties(),
            title = {
                Text(text = "Clear history")
            },
            text = {
                Text(
                    text = "Are you sure want to clear history?",
                    modifier = Modifier.fillMaxWidth()
                )
            },
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    historyViewModel.deleteAllHistory()
                }) {
                    Text(text = "Delete", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                }) {
                    Text(text = "Cancel", color = MaterialTheme.colorScheme.primary)
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.surface,
                    actionIconContentColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.surface
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        goBack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "setting"
                        )
                    }
                },
                actions = {
                    if (historyList.isNotEmpty()) {
                        IconButton(onClick = {
                            showDialog = true
                        }) {
                            Icon(
                                imageVector = Icons.Filled.DeleteSweep,
                                contentDescription = "setting"
                            )
                        }
                    }
                },
                title = { Text(text = "History", fontWeight = FontWeight.Medium) },
            )
        }
    ) {
        if (historyList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
            ) {
                items(historyList) { history ->
                    HistoryItem(
                        modifier = Modifier.padding(4.sdp),
                        history = history,
                        onItemClick = { navigateTranslateScreen(history) },
                        onDeleteClick = { historyViewModel.deleteHistory(history) },
                        onUpdateClick = { newH -> historyViewModel.updateHistory(newH) })
                }
            }
        } else {
            EmptyScreen(Modifier.padding(it))
        }
    }
}

@Composable
fun HistoryItem(
    modifier: Modifier = Modifier,
    history: History,
    showAction: Boolean = true,
    onItemClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: (History) -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp),
        modifier = modifier.fillMaxWidth(),
        onClick = onItemClick,
        tonalElevation = 8.sdp,
        shadowElevation = 0.dp,
        shape = RoundedCornerShape(8.sdp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 15.ssp
                        )
                    ) {
                        append(history.fromLang)
                    }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 18.ssp
                        )
                    ) {
                        append(" \u2192 ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 15.ssp
                        )
                    ) {
                        append(history.toLang)
                    }
                })
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = history.originalText,
                    maxLines = 1,
                    fontSize = 14.ssp,
                    overflow = TextOverflow.Ellipsis
                )
                if (showAction) {
                    Spacer(modifier = Modifier.width(20.sdp))
                    IconButton(onClick = {
                        onUpdateClick(
                            history.copy(
                                isFavorite = history.isFavorite.not()
                            )
                        )
                    }) {
                        Icon(
                            imageVector = if (history.isFavorite) Icons.Filled.Star else Icons.Outlined.StarOutline,
                            contentDescription = "star",
                            tint = if (history.isFavorite) Color.Yellow else Color.Unspecified
                        )
                    }

                    IconButton(onClick = {
                        onDeleteClick()
                    }) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete")
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = history.translateText,
                    maxLines = 1,
                    fontSize = 14.ssp,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(10.sdp))
        }
    }
}

