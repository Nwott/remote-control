package com.sayna.remotecontrol.feature_rc_action.presentation.edit_remote

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayna.remotecontrol.feature_rc_action.domain.use_case.RCActionUseCases
import com.sayna.remotecontrol.feature_rc_action.domain.util.OrderType
import com.sayna.remotecontrol.feature_rc_action.domain.util.RCActionOrder
import com.sayna.remotecontrol.feature_rc_action.presentation.RCActionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EditRemoteViewModel @Inject constructor(
    private val rcActionUseCases: RCActionUseCases,
): ViewModel() {
    private val _state = mutableStateOf(RCActionState())
    val state: State<RCActionState> = _state

    private var getRCActionsJob: Job? = null

    init {
        GetRCActions(RCActionOrder.ID(OrderType.Ascending))
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
