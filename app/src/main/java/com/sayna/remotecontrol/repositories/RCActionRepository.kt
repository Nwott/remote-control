package com.sayna.remotecontrol.repositories

import com.sayna.remotecontrol.models.RCAction

class RCActionRepository {
    private lateinit var remoteControlActions : List<RCAction>

    fun GetRemoteControlActions() : List<RCAction> {
        return remoteControlActions
    }
}