package com.sayna.remotecontrol.feature_rc_action.presentation.edit_remote

import android.hardware.ConsumerIrManager
import androidx.lifecycle.ViewModel
import com.sayna.remotecontrol.feature_rc_action.domain.use_case.RCActionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditRemoteViewModel @Inject constructor(
    private val rcActionUseCases: RCActionUseCases,
): ViewModel() {

}
