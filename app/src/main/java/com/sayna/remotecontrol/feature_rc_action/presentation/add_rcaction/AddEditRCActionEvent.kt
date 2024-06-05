package com.sayna.remotecontrol.feature_rc_action.presentation.add_rcaction

import android.content.Context
import android.net.Uri
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import java.io.File
import java.io.InputStream
import java.io.OutputStream

sealed class AddEditRCActionEvent {
    data class SaveRCAction(val title: String, val frequency: Int, val code: String): AddEditRCActionEvent()
    object DeleteRCAction: AddEditRCActionEvent()
    data class ImportRCActions(val inputStream: InputStream?): AddEditRCActionEvent()
    data class ExportRCActions(val fileDir: File?): AddEditRCActionEvent()
}