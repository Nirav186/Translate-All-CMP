package com.all.language.translate.speech.text.ui.composable.dashboardOne

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.all.language.translate.speech.text.data.model.Language
import com.all.language.translate.speech.text.data.storage.KeyValueStorage
import com.all.language.translate.speech.text.data.storage.KeyValueStorageImpl
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import kotlinx.coroutines.launch

class DashBoardViewModel(private val controller: PermissionsController) : ScreenModel {

    var permissionState by mutableStateOf(PermissionState.NotDetermined)
        private set

    var permissionReqCount by mutableStateOf(0)
        private set

    private val keyValueStorage: KeyValueStorage = KeyValueStorageImpl()

    init {
        screenModelScope.launch {
            permissionState = controller.getPermissionState(Permission.RECORD_AUDIO)
            permissionReqCount = keyValueStorage.isPermissionRequested
        }
    }

    fun swapSelectedLanguage(selectedFromLang: Language, selectedToLang: Language) {
        val (fromLang, toLang) = Pair(selectedFromLang, selectedToLang)
        keyValueStorage.fromLanguageCode = toLang.code
        keyValueStorage.toLanguageCode = fromLang.code
    }

    fun provideOrRequestRecordAudioPermission() {
        screenModelScope.launch {
            if (!controller.isPermissionGranted(Permission.RECORD_AUDIO)) {
                try {
                    controller.providePermission(Permission.RECORD_AUDIO)
                    permissionState = PermissionState.Granted
                } catch (e: DeniedAlwaysException) {
                    permissionState = PermissionState.DeniedAlways
                } catch (e: DeniedException) {
                    permissionState = PermissionState.Denied
                } catch (e: RequestCanceledException) {
                    e.printStackTrace()
                }
                keyValueStorage.isPermissionRequested += 1
                permissionReqCount = keyValueStorage.isPermissionRequested
            } else {
                permissionState = PermissionState.Granted
            }
        }
    }

    fun openAppSettings() {
        controller.openAppSettings()
    }
}