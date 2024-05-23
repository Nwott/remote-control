package com.sayna.remotecontrol.repositories

import android.app.RemoteAction

class RCActionRepository {
    private lateinit var remoteControlActions : List<RemoteAction>;

    fun GetRemoteControlActions() : List<RemoteAction> {
        return remoteControlActions;
    }
}