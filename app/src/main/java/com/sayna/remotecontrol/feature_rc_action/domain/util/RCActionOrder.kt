package com.sayna.remotecontrol.feature_rc_action.domain.util

sealed class RCActionOrder(
    val orderType: OrderType
) {
    class Title(orderType: OrderType): RCActionOrder(orderType)
    class ID(orderType: OrderType): RCActionOrder(orderType)

    fun copy(orderType: OrderType): RCActionOrder {
        return when(this) {
            is Title -> Title(orderType)
            is ID -> ID(orderType)
        }
    }
}