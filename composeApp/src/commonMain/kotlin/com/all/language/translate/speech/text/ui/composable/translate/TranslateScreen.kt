package com.all.language.translate.speech.text.ui.composable.translate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.all.language.translate.speech.text.data.model.History
import com.all.language.translate.speech.text.data.model.Language
import com.all.language.translate.speech.text.data.storage.KeyValueStorage
import com.all.language.translate.speech.text.data.storage.KeyValueStorageImpl
import com.all.language.translate.speech.text.ui.composable.components.PermissionSettingsDialog
import com.all.language.translate.speech.text.ui.composable.components.RotatingSwapIcon
import com.all.language.translate.speech.text.ui.composable.components.TextArea
import com.all.language.translate.speech.text.ui.composable.dashboardOne.DashBoardViewModel
import com.all.language.translate.speech.text.ui.composable.selection.LanguageSelectionScreen
import com.all.language.translate.speech.text.utils.Constant
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import kotlinx.coroutines.launch
import multiplatform.network.cmptoast.showToast
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource
import translate.composeapp.generated.resources.Res
import translate.composeapp.generated.resources.ic_arroe_down_small

class TranslateScreen(private val history: History? = null) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val factory = rememberPermissionsControllerFactory()
        val controller = remember(factory) {
            factory.createPermissionsController()
        }

        BindEffect(controller)

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
    val clipboardManager = LocalClipboardManager.current
    var isListening by remember { mutableStateOf(false) }
    var showPermissionDeniedDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

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
    }) {
        Box(modifier = Modifier.padding(it).fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            ) {
                Card(
                    modifier = Modifier.padding(horizontal = 8.sdp, vertical = 4.sdp).fillMaxWidth()
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
                                modifier = Modifier.size(14.sdp),
                                painter = painterResource(Res.drawable.ic_arroe_down_small),
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
                                modifier = Modifier.size(14.sdp),
                                painter = painterResource(Res.drawable.ic_arroe_down_small),
                                contentDescription = "drop down"
                            )
                        }
                    }
                }

                Card(
                    modifier = Modifier.padding(horizontal = 8.sdp, vertical = 4.sdp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(1.sdp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(0.dp)
                    ),
                    shape = RoundedCornerShape(8.sdp),
                ) {
                    Box(
                        modifier = Modifier.padding(horizontal = 4.sdp)
                    ) {
                        TextArea(
                            modifier = Modifier
                                .padding(8.sdp)
                                .padding(top = 20.sdp)
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
                                    text = "Enter your text", fontSize = 13.ssp
                                )
                            },
                            text = historyLocal.originalText,
                            onValueChange = { q ->
                                translateViewModel.updateOriginalText(q)
                            })

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = {
                                if (selectedFromLang.isActive.not()) {
                                    showToast(message = "Speech output isn't available for ${selectedFromLang.name}")
                                } else {
                                    Constant.textToSpeechService.speak(historyLocal.originalText)
                                }
                            }) {
                                Icon(
                                    imageVector = if (historyLocal.originalText.isNotEmpty() && selectedFromLang.isActive) Icons.AutoMirrored.Filled.VolumeUp else Icons.AutoMirrored.Filled.VolumeOff,
                                    contentDescription = ""
                                )
                            }
                            Text(text = selectedFromLang.name, fontSize = 11.ssp)
                            Spacer(modifier = Modifier.weight(1f))
                            if (historyLocal.originalText.isEmpty().not()) {
                                IconButton(onClick = {
                                    translateViewModel.clear()
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = "close"
                                    )
                                }
                            }
                            if (historyLocal.originalText.isEmpty()) {
                                IconButton(
                                    onClick = {
                                        scope.launch {
                                            when (dashBoardViewModel.permissionState) {
                                                PermissionState.Granted -> {
                                                    if (isListening) {
                                                        isListening = false
                                                        Constant.speechToTextService.stopListening()
                                                    } else {
                                                        isListening = true
                                                        Constant.speechToTextService.startListening(
                                                            onResult = { text ->
                                                                translateViewModel.updateOriginalText(
                                                                    text
                                                                )
                                                                isListening = false
                                                            },
                                                            onError = { errorMsg ->
                                                                showToast(errorMsg)
                                                                isListening = false
                                                            }
                                                        )
                                                    }
                                                }

                                                PermissionState.DeniedAlways -> {
                                                    showPermissionDeniedDialog = true
                                                }

                                                else -> {
                                                    if (dashBoardViewModel.permissionReqCount >= 1) {
                                                        showPermissionDeniedDialog = true
                                                    } else {
                                                        dashBoardViewModel.provideOrRequestRecordAudioPermission()
                                                    }
                                                }
                                            }
                                        }
                                    }
                                ) {
                                    Icon(imageVector = Icons.Filled.Mic, contentDescription = "mic")
                                }
                            }
                        }

                        if (historyLocal.originalText.isEmpty().not()) {
                            Button(modifier = Modifier.padding(4.sdp).align(Alignment.BottomEnd),
                                contentPadding = PaddingValues(
                                    horizontal = 8.sdp,
                                    vertical = 4.sdp
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.surface
                                ),
                                shape = RoundedCornerShape(8.sdp),
                                onClick = {
                                    translateViewModel.translate()
                                }) {
                                Text(
                                    modifier = Modifier.padding(vertical = 4.sdp),
                                    text = "Translate",
                                    fontSize = 14.ssp,
                                )
                            }
                        }
                    }
                }

                if (historyLocal.translateText.isEmpty().not()) {
                    Card(
                        modifier = Modifier.padding(horizontal = 8.sdp, vertical = 4.sdp)
                            .heightIn(min = 130.sdp, max = 150.sdp).fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(1.sdp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(0.dp)
                        ),
                        shape = RoundedCornerShape(8.sdp),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize().padding(horizontal = 4.sdp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(onClick = {
                                    if (selectedToLang.isActive.not()) {
                                        showToast(message = "Speech output isn't available for ${selectedToLang.name}")
                                    } else {
                                        Constant.textToSpeechService.speak(historyLocal.translateText)
                                    }
                                }) {
                                    Icon(
                                        imageVector = if (historyLocal.translateText.isNotEmpty() && selectedToLang.isActive)
                                            Icons.AutoMirrored.Filled.VolumeUp else Icons.AutoMirrored.Filled.VolumeOff,
                                        contentDescription = ""
                                    )
                                }
                                Text(
                                    text = selectedToLang.name,
                                    fontSize = 11.ssp
                                )
                                Spacer(modifier = Modifier.weight(1f))
                            }

                            Text(
                                modifier = Modifier
                                    .padding(8.sdp)
                                    .padding(top = 18.sdp)
                                    .fillMaxWidth(),
                                text = historyLocal.translateText,
                                maxLines = 8
                            )

                            IconButton(
                                modifier = Modifier.align(Alignment.TopEnd),
                                onClick = {
                                    translateViewModel.toggleFavorite()
                                }) {
                                Icon(
                                    imageVector = if (historyLocal.isFavorite) Icons.Filled.Star else Icons.Outlined.StarOutline,
                                    contentDescription = "star",
                                    tint = if (historyLocal.isFavorite) Color.Yellow else Color.Unspecified
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
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
                                    Icon(
                                        imageVector = Icons.Filled.Share,
                                        contentDescription = "share"
                                    )
                                }
                                Spacer(modifier = Modifier.height(5.sdp))
                            }
                        }
                    }
                }
            }

            if (showPermissionDeniedDialog) {
                PermissionSettingsDialog(
                    permissionState = dashBoardViewModel.permissionState,
                    title = "Permission Required",
                    message = "This feature requires access to your microphone. Please grant the permission in the app settings.",
                    onDismissRequest = {
                        showPermissionDeniedDialog = false
                    },
                    onSettingsClick = {
                        if (dashBoardViewModel.permissionState == PermissionState.DeniedAlways) {
                            dashBoardViewModel.openAppSettings()
                        } else if (dashBoardViewModel.permissionReqCount >= 2) {
                            dashBoardViewModel.provideOrRequestRecordAudioPermission()
                            dashBoardViewModel.openAppSettings()
                        } else {
                            dashBoardViewModel.provideOrRequestRecordAudioPermission()
                        }
                        showPermissionDeniedDialog = false
                    }
                )
            }
        }
    }
}

