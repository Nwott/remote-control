package com.sayna.remotecontrol.feature_rc_action.presentation.remote

import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction

data class RCActionState(
    val rcActions: List<RCAction> = emptyList()
)