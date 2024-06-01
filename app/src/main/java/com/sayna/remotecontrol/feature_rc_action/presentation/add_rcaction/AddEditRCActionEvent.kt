package com.sayna.remotecontrol.feature_rc_action.presentation.add_rcaction

import android.content.Context
import android.net.Uri
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction

sealed class AddEditRCActionEvent {
    data class SaveRCAction(val title: String, val frequency: Int, val code: String): AddEditRCActionEvent()
    object DeleteRCAction: AddEditRCActionEvent()
    data class ImportRCActions(val uri: Uri): AddEditRCActionEvent()
}