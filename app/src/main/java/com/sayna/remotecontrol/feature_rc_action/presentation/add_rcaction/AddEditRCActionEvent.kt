package com.sayna.remotecontrol.feature_rc_action.presentation.add_rcaction

import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction

sealed class AddEditRCActionEvent {
    data class SaveRCAction(val title: String, val frequency: Int, val code: String): AddEditRCActionEvent()
    data class DeleteRCAction(val rcAction: RCAction)
}