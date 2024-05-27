package com.sayna.remotecontrol.feature_rc_action.presentation.util

sealed class Screen(val route: String) {
    object RemoteScreen: Screen("remote_screen")
    object EditRemoteScreen: Screen("edit_remote_screen")
    object AddRCActionScreen: Screen("add_rc_action_screen")
}