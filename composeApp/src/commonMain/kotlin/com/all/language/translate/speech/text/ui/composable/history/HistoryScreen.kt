package com.all.language.translate.speech.text.ui.composable.history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.all.language.translate.speech.text.data.model.History
import com.all.language.translate.speech.text.ui.composable.components.EmptyScreen
import com.all.language.translate.speech.text.ui.composable.translate.TranslateScreen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Heart
import compose.icons.fontawesomeicons.regular.TrashAlt
import compose.icons.fontawesomeicons.solid.Heart
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource
import translate.composeapp.generated.resources.Res
import translate.composeapp.generated.resources.emptyList
import translate.composeapp.generated.resources.unfavorite

class HistoryScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val historyViewModel = rememberScreenModel { HistoryViewModel() }
        LaunchedEffect(true) {
            historyViewModel.getAllHistory()
        }
        HistoryScreenContent(historyViewModel = historyViewModel, goBack = {
            navigator.pop()
        }, navigateTranslateScreen = {
            navigator.push(TranslateScreen(it))
        })
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
        AlertDialog(properties = DialogProperties(), title = {
            Text(text = "Clear history")
        }, text = {
            Text(
                text = "Are you sure want to clear history?", modifier = Modifier.fillMaxWidth()
            )
        }, onDismissRequest = { showDialog = false }, confirmButton = {
            TextButton(onClick = {
                historyViewModel.deleteAllHistory()
            }) {
                Text(text = "Delete", color = Color.Red)
            }
        }, dismissButton = {
            TextButton(onClick = {
                showDialog = false
            }) {
                Text(text = "Cancel", color = MaterialTheme.colorScheme.primary)
            }
        })
    }

    Scaffold(topBar = {
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
                            painter = painterResource(Res.drawable.emptyList),
                            contentDescription = "empty list"
                        )
                    }
                }
            },
            title = { Text(text = "History", fontWeight = FontWeight.Medium) },
        )
    }) {
        if (historyList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.padding(it).fillMaxSize(),
            ) {
                items(historyList) { history ->
                    HistoryItem(modifier = Modifier.padding(4.sdp),
                        history = history,
                        isFavoriteScreen = false,
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
    isFavoriteScreen: Boolean = true,
    onItemClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onUpdateClick: (History) -> Unit
) {
    Surface(
        color = Color(0xFFF2FAFF),
        modifier = modifier.fillMaxWidth(),
        onClick = onItemClick,
        tonalElevation = 8.sdp,
        shadowElevation = 0.dp,
        border = BorderStroke(width = 1.sdp, color = Color(0xFF98C4EC)),
        shape = RoundedCornerShape(12.sdp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(start = 8.sdp).weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary, fontSize = 14.ssp
                            )
                        ) {
                            append(history.fromLang)
                        }
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.onSurface, fontSize = 18.ssp
                            )
                        ) {
                            append(" \u2192 ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary, fontSize = 14.ssp
                            )
                        ) {
                            append(history.toLang)
                        }
                    }, fontWeight = W400)
                }
                Spacer(modifier = Modifier.height(10.sdp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = history.originalText,
                        maxLines = 1,
                        color = Color.Black,
                        fontWeight = W400,
                        fontSize = 14.ssp,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = history.translateText,
                        maxLines = 1,
                        fontSize = 14.ssp,
                        color = Color.Gray,
                        fontWeight = W400,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(10.sdp))
            }
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                if (!isFavoriteScreen) {
                    IconButton(onClick = { onDeleteClick() }) {
                        Icon(
                            imageVector = FontAwesomeIcons.Regular.TrashAlt,
                            contentDescription = "delete",
                            modifier = Modifier.size(14.sdp),
                            tint = Color(0xFF355481)
                        )
                    }
                    IconButton(onClick = { onUpdateClick(history.copy(isFavorite = history.isFavorite.not())) }) {
                        Icon(
                            imageVector = if (history.isFavorite) FontAwesomeIcons.Solid.Heart else FontAwesomeIcons.Regular.Heart,
                            contentDescription = "star",
                            modifier = Modifier.size(14.sdp),
                            tint = Color(0xFF355481)
                        )
                    }
                } else {
                    IconButton(onClick = { onDeleteClick() }) {
                        Icon(
                            painter = painterResource(Res.drawable.unfavorite),
                            contentDescription = "delete",
                            modifier = Modifier.size(18.sdp),
                            tint = Color(0xFF355481)
                        )
                    }
                }
            }
        }
    }
}

