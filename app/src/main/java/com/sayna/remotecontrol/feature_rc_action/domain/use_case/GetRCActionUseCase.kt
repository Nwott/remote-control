package com.sayna.remotecontrol.feature_rc_action.domain.use_case

import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import com.sayna.remotecontrol.feature_rc_action.domain.repository.RCActionRepository

class GetRCActionUseCase(
    private val repository: RCActionRepository
) {
    suspend operator fun invoke(id: Int): RCAction? {
        return repository.GetRCActionById(id)
    }
}