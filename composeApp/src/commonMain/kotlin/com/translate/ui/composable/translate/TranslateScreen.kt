package com.translate.ui.composable.translate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.translate.data.model.History
import com.translate.data.model.Language
import com.translate.data.storage.KeyValueStorage
import com.translate.data.storage.KeyValueStorageImpl
import com.translate.ui.composable.components.RotatingSwapIcon
import com.translate.ui.composable.components.TextArea
import com.translate.ui.composable.dashboardOne.DashBoardViewModel
import com.translate.ui.composable.selection.LanguageSelectionScreen
import com.translate.utils.Constant
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import multiplatform.network.cmptoast.showToast
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

class TranslateScreen(
    private val history: History? = null
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val factory = rememberPermissionsControllerFactory()
        val controller = remember(factory) {
            factory.createPermissionsController()
        }

        val dashBoardViewModel = rememberScreenModel { DashBoardViewModel(controller) }
        val translateViewModel = rememberScreenModel { TranslateViewModel() }

        val keyValueStorage: KeyValueStorage = KeyValueStorageImpl()
        val selectedFromLang by keyValueStorage.observableFromLanguage.collectAsState(initial = Constant.languageList.first())
        val selectedToLang by keyValueStorage.observableToLanguage.collectAsState(initial = Constant.languageList.first())

        LaunchedEffect(true) {
            translateViewModel.init(history, selectedFromLang, selectedToLang)
        }

        TranslateScreenContent(
            dashBoardViewModel = dashBoardViewModel,
            selectedFromLang = selectedFromLang,
            selectedToLang = selectedToLang,
            onBack = { navigator.pop() },
            navigateToLanguageSelection = { navigator.push(LanguageSelectionScreen(it)) },
            translateViewModel = translateViewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslateScreenContent(
    dashBoardViewModel: DashBoardViewModel,
    selectedFromLang: Language,
    selectedToLang: Language,
    onBack: () -> Unit,
    navigateToLanguageSelection: (Boolean) -> Unit,
    translateViewModel: TranslateViewModel
) {
    val historyLocal by translateViewModel.history.collectAsState()
    LocalClipboardManager.current
    val clipboardManager = LocalClipboardManager.current
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
                        onBack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "setting"
                        )
                    }
                },
                title = { Text(text = "Translate") },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.sdp, vertical = 4.sdp)
                    .fillMaxWidth()
                    .height(40.sdp),
                elevation = CardDefaults.cardElevation(1.sdp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(0.dp)
                ),
                shape = RoundedCornerShape(8.sdp),
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = {
                        navigateToLanguageSelection(true)
                    }) {
                        Text(text = selectedFromLang.name)
                        Spacer(modifier = Modifier.width(2.sdp))
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "drop down"
                        )
                    }
                    RotatingSwapIcon(
                        dashBoardViewModel = dashBoardViewModel,
                        selectedFromLang = selectedFromLang,
                        selectedToLang = selectedToLang
                    )
                    TextButton(onClick = {
                        navigateToLanguageSelection(false)
                    }) {
                        Text(text = selectedToLang.name)
                        Spacer(modifier = Modifier.width(2.sdp))
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "drop down"
                        )
                    }
                }
            }

            Card(
                modifier = Modifier
                    .padding(horizontal = 8.sdp, vertical = 4.sdp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(1.sdp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(0.dp)
                ),
                shape = RoundedCornerShape(8.sdp),
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 4.sdp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            if (selectedFromLang.isActive.not()) {
                                showToast(message = "Speech output isn't available for ${selectedFromLang.name}")
                            }
                        }) {
                            Icon(
                                imageVector = if (historyLocal.originalText.isEmpty()
                                        .not()
                                ) Icons.AutoMirrored.Filled.VolumeUp else Icons.AutoMirrored.Filled.VolumeOff,
                                contentDescription = ""
                            )
                        }
                        Text(text = selectedFromLang.name, fontSize = 11.ssp)
                        Spacer(modifier = Modifier.weight(1f))

                        if (historyLocal.originalText.isEmpty().not()) {
                            IconButton(
                                onClick = {
                                    translateViewModel.clear()
                                }) {
                                Icon(imageVector = Icons.Filled.Close, contentDescription = "close")
                            }
                        }

                        if (historyLocal.originalText.isEmpty()) {
                            IconButton(
                                onClick = { }) {
                                Icon(imageVector = Icons.Filled.Mic, contentDescription = "mic")
                            }
                        }
                    }

                    TextArea(
                        modifier = Modifier
                            .padding(horizontal = 8.sdp)
                            .fillMaxWidth()
                            .heightIn(min = 130.sdp, max = 150.sdp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.primary,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        placeholder = {
                            Text(
                                text = "Enter your text",
                                fontSize = 14.ssp
                            )
                        },
                        text = historyLocal.originalText,
                        onValueChange = { q ->
                            translateViewModel.updateOriginalText(q)
                        }
                    )

                    if (historyLocal.originalText.isEmpty().not()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.sdp),
                            horizontalAlignment = Alignment.End
                        ) {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.surface
                                ),
                                shape = RoundedCornerShape(8.sdp),
                                onClick = {
                                    translateViewModel.translate()
                                }
                            ) {
                                Text(
                                    modifier = Modifier.padding(vertical = 4.sdp),
                                    text = "Translate",
                                    fontSize = 16.ssp,
                                )
                            }
                        }
                    }
                }
            }

            if (historyLocal.translateText.isEmpty().not()) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 8.sdp, vertical = 4.sdp)
                        .heightIn(min = 130.sdp, max = 150.sdp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(1.sdp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(0.dp)
                    ),
                    shape = RoundedCornerShape(8.sdp),
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 4.sdp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = {
                                if (selectedFromLang.isActive.not()) {
                                    showToast(message = "Speech output isn't available for ${historyLocal.toLang}")
                                }
                            }) {
                                Icon(
                                    imageVector = if (historyLocal.translateText.isEmpty()
                                            .not()
                                    ) Icons.AutoMirrored.Filled.VolumeUp else Icons.AutoMirrored.Filled.VolumeOff,
                                    contentDescription = ""
                                )
                            }
                            Text(text = selectedToLang.name, fontSize = 11.ssp)
                            Spacer(modifier = Modifier.weight(1f))

                            IconButton(onClick = {
                                translateViewModel.toggleFavorite()
                            }) {
                                Icon(
                                    imageVector = if (historyLocal.isFavorite) Icons.Filled.Star else Icons.Outlined.StarOutline,
                                    contentDescription = "star",
                                    tint = if (historyLocal.isFavorite) Color.Yellow else Color.Unspecified
                                )
                            }
                        }

                        Text(
                            modifier = Modifier
                                .padding(horizontal = 8.sdp)
                                .fillMaxWidth()
                                .weight(1f),
                            text = historyLocal.translateText,
                            maxLines = 8
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.sdp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(onClick = {
                                clipboardManager.setText(AnnotatedString(text = historyLocal.translateText))
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.ContentCopy,
                                    contentDescription = "copy all"
                                )
                            }
                            Spacer(modifier = Modifier.height(5.sdp))
                            IconButton(onClick = {
                                translateViewModel.shareText()
                            }) {
                                Icon(imageVector = Icons.Filled.Share, contentDescription = "share")
                            }
                            Spacer(modifier = Modifier.height(5.sdp))
                            /*         IconButton(onClick = {  }) {
                                Icon(
                                    imageVector = Icons.Filled.CropFree,
                                    contentDescription = "Expand"
                                )
                            }*/
                        }
                    }
                }
            }
        }
    }
}

