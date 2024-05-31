package com.sayna.remotecontrol.feature_rc_action.presentation.remote

import android.hardware.ConsumerIrManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
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
class RemoteViewModel @Inject constructor(
    private val rcActionUseCases: RCActionUseCases,
    private val irManager: ConsumerIrManager
): ViewModel() {
    // create state
    private val _state = mutableStateOf(RCActionState())
    val state: State<RCActionState> = _state

    private var getRCActionsJob: Job? = null

    init {
        GetRCActions(RCActionOrder.ID(OrderType.Ascending))
    }

    fun OnEvent(event: RemoteEvent) {
        when(event) {
            is RemoteEvent.PerformRCAction -> {
                // try to emit infrared signal
                if(irManager.hasIrEmitter()) {
                    // parse code in string to intarray
                    EmitIR(event.rcAction, ParseIRCode(event.rcAction))
                }
                else {
                    println("error something")
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

    /* converts sendir IR code format to int array to be emitted from ir manager */
    private fun ParseIRCode(rcAction: RCAction): IntArray {
        val code: String = rcAction.code
        val intList : MutableList<Int> = mutableListOf()

        val strList = code.split(" ")

        strList.forEach() {
            try {
                var str = it.replace("\\s+".toRegex(), " ")

                intList.add(str.toInt() * (1000000 / rcAction.frequency))
            } catch(e: Exception) {

            }
        }

        return intList.toIntArray()
    }

    /* emits the ir code */
    private fun EmitIR(rcAction: RCAction, pattern: IntArray) {
        println("code: " + pattern.toString())
        irManager.transmit(rcAction.frequency, pattern)
    }
}