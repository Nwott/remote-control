package com.sayna.remotecontrol.feature_rc_action.presentation.remote

import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import com.sayna.remotecontrol.feature_rc_action.domain.util.OrderType
import com.sayna.remotecontrol.feature_rc_action.domain.util.RCActionOrder

data class RCActionState(
    val rcActions: List<RCAction> = emptyList(),
    val rcActionOrder: RCActionOrder = RCActionOrder.Title(OrderType.Descending)
)