package com.sayna.remotecontrol.feature_rc_action.data.repository

import com.sayna.remotecontrol.feature_rc_action.data.data_source.RCActionDao
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import com.sayna.remotecontrol.feature_rc_action.domain.repository.RCActionRepository
import kotlinx.coroutines.flow.Flow

class RCActionRepositoryImpl(
    private val dao: RCActionDao
) : RCActionRepository {

    override fun GetRCActions(): Flow<List<RCAction>> {
        return dao.GetRCActions()
    }

    override suspend fun GetRCActionById(id: Int): RCAction? {
        return dao.GetRCActionById(id)
    }

    override suspend fun InsertRCAction(rcAction: RCAction) {
        return dao.InsertRCAction(rcAction)
    }

    override suspend fun DeleteRCAction(rcAction: RCAction) {
        return dao.DeleteRCAction(rcAction)
    }
}