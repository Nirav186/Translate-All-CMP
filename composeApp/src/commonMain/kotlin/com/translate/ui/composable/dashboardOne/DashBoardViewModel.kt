package com.translate.ui.composable.dashboardOne

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.translate.data.model.Language
import com.translate.data.storage.KeyValueStorage
import com.translate.data.storage.KeyValueStorageImpl
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import kotlinx.coroutines.launch

class DashBoardViewModel(/*private val controller: PermissionsController*/) : ScreenModel {

//    var permissionState by mutableStateOf(PermissionState.NotDetermined)
//        private set
//
//    init {
//        screenModelScope.launch {
//            permissionState = controller.getPermissionState(Permission.RECORD_AUDIO)
//        }
//    }

    fun swapSelectedLanguage(selectedFromLang: Language, selectedToLang: Language) {
        val keyValueStorage: KeyValueStorage = KeyValueStorageImpl()
        val (fromLang, toLang) = Pair(selectedFromLang, selectedToLang)
        keyValueStorage.fromLanguageCode = toLang.code
        keyValueStorage.toLanguageCode = fromLang.code
    }

//    fun provideOrRequestRecordAudioPermission() {
//        screenModelScope.launch {
//            try {
//                controller.providePermission(Permission.RECORD_AUDIO)
//                permissionState = PermissionState.Granted
//            } catch(e: DeniedAlwaysException) {
//                permissionState = PermissionState.DeniedAlways
//            } catch(e: DeniedException) {
//                permissionState = PermissionState.Denied
//            } catch(e: RequestCanceledException) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    fun openAppSettings() {
//        controller.openAppSettings()
//    }
}