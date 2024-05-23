package com.sayna.remotecontrol.feature_rc_action.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}