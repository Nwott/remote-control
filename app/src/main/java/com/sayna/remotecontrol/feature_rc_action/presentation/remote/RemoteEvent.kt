package com.sayna.remotecontrol.feature_rc_action.presentation.remote

import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction

sealed class RemoteEvent {
    data class PerformRCAction(val rcAction: RCAction): RemoteEvent()
}