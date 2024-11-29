package com.translate.ui.composable.dashboardOne

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalLifecycleOwner
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.translate.ui.composable.favorite.FavoriteScreen
import com.translate.ui.composable.history.HistoryScreen
import com.translate.ui.composable.idioms.IdiomScreen
import com.translate.ui.composable.selection.LanguageSelectionScreen
import com.translate.ui.composable.translate.TranslateScreen
import com.translate.utils.Constant
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import kotlinx.coroutines.launch

class DashBoardScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val scaffoldState = rememberScaffoldState()

        val dashBoardViewModel = rememberScreenModel {
            DashBoardViewModel(
                permissionsController = Constant.permissionsController,
                permissionType = Permission.RECORD_AUDIO,
                eventsDispatcher = Constant.eventsDispatcherOnMain
            )
        }

        val coroutineScope = rememberCoroutineScope()

        val eventsListener = remember {
            object : DashBoardViewModel.EventListener {
                override fun onSuccess() {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Permission successfully granted!"
                        )
                    }
                }

                override fun onDenied(exception: DeniedException) {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Permission denied!"
                        )
                    }
                }

                override fun onDeniedAlways(exception: DeniedAlwaysException) {
                    coroutineScope.launch {
                        val result = scaffoldState.snackbarHostState.showSnackbar(
                            message = "Permission is always denied",
                            duration = SnackbarDuration.Long,
                            actionLabel = "Settings"
                        )

                        if (result == SnackbarResult.ActionPerformed) {
                            dashBoardViewModel.permissionsController.openAppSettings()
                        }
                    }
                }
            }
        }

//        val lifecycleOwner = LocalLifecycleOwner.current
//        LaunchedEffect(true) {
//            dashBoardViewModel.eventsDispatcher.(lifecycleOwner, eventsListener)
//        }
//        val permissionState by dashBoardViewModel.permissionState.collectAsState()

        BindEffect(dashBoardViewModel.permissionsController)

        DashBoardContent(
            dashBoardViewModel = dashBoardViewModel,
            navigateToLanguageSelection = {
                navigator.push(LanguageSelectionScreen(it))
            },
            navigateToTranslateScreen = {
                navigator.push(TranslateScreen())
            },
            navigateToHistoryScreen = {
                navigator.push(HistoryScreen())
            },
            navigateToIdiomsListScreen = {
                navigator.push(IdiomScreen())
            },
            navigateToFavoriteScreen = {
                navigator.push(FavoriteScreen())
            }
        )
    }
}