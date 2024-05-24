package com.sayna.remotecontrol.feature_rc_action.presentation.util

sealed class Screen(val route: String) {
    object RemoteScreen: Screen("remote_screen")
}