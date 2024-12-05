package com.all.language.translate.speech.text.ui.composable.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.DialogProperties
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.all.language.translate.speech.text.data.model.History
import com.all.language.translate.speech.text.ui.composable.components.EmptyScreen
import com.all.language.translate.speech.text.ui.composable.history.HistoryItem
import com.all.language.translate.speech.text.ui.composable.translate.TranslateScreen
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.painterResource
import translate.composeapp.generated.resources.Res
import translate.composeapp.generated.resources.emptyList

class FavoriteScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val favoriteViewModel = rememberScreenModel { FavoriteViewModel() }
        LaunchedEffect(true) {
            favoriteViewModel.getAllHistory()
        }
        FavoriteScreenContent(
            favoriteViewModel = favoriteViewModel,
            navigateTranslateScreen = { history -> navigator.push(TranslateScreen(history)) },
            goBack = { navigator.pop() },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreenContent(
    favoriteViewModel: FavoriteViewModel,
    navigateTranslateScreen: (History) -> Unit,
    goBack: () -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }
    val historyList by favoriteViewModel.favoriteList.collectAsState()

    if (showDialog) {
        AlertDialog(properties = DialogProperties(), title = {
            Text(text = "Remove all Favorites")
        }, text = {
            Text(
                text = "Are you sure want to Remove all Favorites ?",
                modifier = Modifier.fillMaxWidth()
            )
        }, onDismissRequest = { showDialog = false }, confirmButton = {
            TextButton(onClick = {
                favoriteViewModel.removeAllFavorites()
                showDialog = false
            }) {
                Text(text = "Remove", color = Color.Red)
            }
        }, dismissButton = {
            TextButton(onClick = {
                showDialog = false
            }) {
                Text(text = "Cancel", color = Color.Gray)
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
                IconButton(onClick = { goBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "setting"
                    )
                }
            },
            actions = {
                if (historyList.isNotEmpty()) {
                    IconButton(onClick = { showDialog = true }) {
                        Icon(
                            painter = painterResource(Res.drawable.emptyList),
                            contentDescription = "empty list"
                        )
                    }
                }
            },
            title = { Text(text = "Favorite", fontWeight = FontWeight.Medium) },
        )
    }) {
        if (historyList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.padding(it).fillMaxSize(),
            ) {
                items(historyList.filter { itm -> itm.isFavorite }) { history ->
                    HistoryItem(modifier = Modifier.padding(4.sdp),
                        history = history,
                        isFavoriteScreen = true,
                        onItemClick = { navigateTranslateScreen(history) },
                        onDeleteClick = { favoriteViewModel.removeFavorite(history) },
                        onUpdateClick = { newH -> })
                }
            }
        } else {
            EmptyScreen(Modifier.padding(it))
        }
    }
}