package com.translate.ui.composable.dashboardOne

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.translate.data.model.Language
import com.translate.data.storage.KeyValueStorage
import com.translate.data.storage.KeyValueStorageImpl
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcherOwner
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashBoardViewModel(
    val permissionsController: PermissionsController,
    private val permissionType: Permission,
    override val eventsDispatcher: EventsDispatcher<EventListener>,
) : ScreenModel, EventsDispatcherOwner<DashBoardViewModel.EventListener> {

    var permissionState = MutableStateFlow(PermissionState.NotDetermined)

    init {
        screenModelScope.launch {
            permissionState.update { permissionsController.getPermissionState(permissionType) }
            println(permissionState)
        }
    }

    fun swapSelectedLanguage(selectedFromLang: Language, selectedToLang: Language) {
        val keyValueStorage: KeyValueStorage = KeyValueStorageImpl()
        val (fromLang, toLang) = Pair(selectedFromLang, selectedToLang)
        keyValueStorage.fromLanguageCode = toLang.code
        keyValueStorage.toLanguageCode = fromLang.code
    }

    fun provideOrRequestRecordAudioPermission() {
        screenModelScope.launch {
            try {
                permissionsController.providePermission(Permission.RECORD_AUDIO)
                permissionState.value = PermissionState.Granted
            } catch (e: DeniedAlwaysException) {
                permissionState.value = PermissionState.DeniedAlways
            } catch (e: DeniedException) {
                permissionState.value = PermissionState.Denied
            } catch (e: RequestCanceledException) {
                e.printStackTrace()
            }
        }
    }

    fun openAppSettings() {
        permissionsController.openAppSettings()
    }

    interface EventListener {

        fun onSuccess()

        fun onDenied(exception: DeniedException)

        fun onDeniedAlways(exception: DeniedAlwaysException)
    }
}