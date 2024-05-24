package com.sayna.remotecontrol.feature_rc_action.presentation.remote

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import com.sayna.remotecontrol.feature_rc_action.domain.use_case.RCActionUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RemoteViewModel @Inject constructor(
    private val rcActionUseCases: RCActionUseCases
): ViewModel() {
    // create state
    private val _state = mutableStateOf(RCActionState())
    val state: State<RCActionState> = _state

    fun OnEvent(event: RemoteEvent) {
        when(event) {
            is RemoteEvent.PerformRCAction -> {

            }
        }
    }
}