package com.sayna.remotecontrol.feature_rc_action.domain.use_case

import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import com.sayna.remotecontrol.feature_rc_action.domain.repository.RCActionRepository

class DeleteRCActionUseCase(
    private val repository: RCActionRepository
) {
    suspend operator fun invoke(rcAction: RCAction) {
        repository.DeleteRCAction(rcAction)
    }
}