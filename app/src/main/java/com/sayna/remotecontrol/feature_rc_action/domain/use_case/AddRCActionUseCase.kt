package com.sayna.remotecontrol.feature_rc_action.domain.use_case

import com.sayna.remotecontrol.feature_rc_action.domain.model.InvalidRCActionException
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import com.sayna.remotecontrol.feature_rc_action.domain.repository.RCActionRepository

class AddRCActionUseCase(
    private val repository: RCActionRepository
) {
    @Throws(InvalidRCActionException::class)
    suspend operator fun invoke(rcAction: RCAction) {
        if(rcAction.title.isBlank()) {
            throw InvalidRCActionException("The title of the remote control action cannot be empty.")
        }
        if(rcAction.code.isBlank()) {
            throw InvalidRCActionException("The code of the remote control action cannot be empty.")
        }

        repository.InsertRCAction(rcAction)
    }
}