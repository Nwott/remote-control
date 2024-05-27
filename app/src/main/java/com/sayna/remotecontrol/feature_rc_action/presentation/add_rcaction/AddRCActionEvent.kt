package com.sayna.remotecontrol.feature_rc_action.presentation.add_rcaction

sealed class AddRCActionEvent {
    data class SaveRCAction(val title: String, val frequency: Int, val code: String): AddRCActionEvent()
}