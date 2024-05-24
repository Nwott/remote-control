package com.sayna.remotecontrol.feature_rc_action.presentation.remote

import android.hardware.ConsumerIrManager
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import com.sayna.remotecontrol.feature_rc_action.domain.use_case.RCActionUseCases
import com.sayna.remotecontrol.feature_rc_action.domain.util.OrderType
import com.sayna.remotecontrol.feature_rc_action.domain.util.RCActionOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RemoteViewModel @Inject constructor(
    private val rcActionUseCases: RCActionUseCases,
    private val irManager: ConsumerIrManager
): ViewModel() {
    // create state
    private val _state = mutableStateOf(RCActionState())
    val state: State<RCActionState> = _state

    private var getRCActionsJob: Job? = null

    // TODO: Change this to sort by id
    init {
        GetRCActions(RCActionOrder.Title(OrderType.Descending))
    }

    fun OnEvent(event: RemoteEvent) {
        when(event) {
            is RemoteEvent.PerformRCAction -> {
                // try to emit infrared signal
                if(irManager.hasIrEmitter()) {

                }
                else {
                    // TODO: output error message
                }
            }
        }
    }

    private fun GetRCActions(rcActionOrder: RCActionOrder) {
        getRCActionsJob?.cancel()
        rcActionUseCases.getRCActionsUseCase(rcActionOrder)
            .onEach { rcActions ->
                _state.value = state.value.copy(
                    rcActions = rcActions,
                    rcActionOrder = rcActionOrder
                )
            }
            .launchIn(viewModelScope)
    }
}