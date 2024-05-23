package com.sayna.remotecontrol.feature_rc_action.domain.repository

import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import kotlinx.coroutines.flow.Flow

interface RCActionRepository {
    fun GetRCActions(): Flow<List<RCAction>>

    suspend fun GetRCActionById(id: Int) : RCAction?

    suspend fun InsertRCAction(rcAction: RCAction)

    suspend fun DeleteRCAction(rcAction: RCAction)
}