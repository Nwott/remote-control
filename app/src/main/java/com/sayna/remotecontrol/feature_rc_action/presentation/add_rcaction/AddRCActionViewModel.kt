package com.sayna.remotecontrol.feature_rc_action.presentation.add_rcaction

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayna.remotecontrol.feature_rc_action.domain.model.InvalidRCActionException
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import com.sayna.remotecontrol.feature_rc_action.domain.use_case.RCActionUseCases
import com.sayna.remotecontrol.ui.theme.Purple40
import com.sayna.remotecontrol.ui.theme.RedPink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRCActionViewModel @Inject constructor(
    private val rcActionUseCases: RCActionUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _rcActionTitle = mutableStateOf("")
    val rcActionTitle: State<String> = _rcActionTitle

    private val _rcActionFrequency = mutableStateOf(-1)
    val rcActionFrequency: State<Int> = _rcActionFrequency

    private val _rcActionCode = mutableStateOf("")
    val rcActionCode: State<String> = _rcActionCode

    private val _rcActionColor = mutableStateOf(RedPink.toArgb())
    val rcActionColor: State<Int> = _rcActionColor

    private var currentRCActionId : Int? = null

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Int>("rcActionId")?.let { rcActionId->
            if(rcActionId != -1) {
                viewModelScope.launch {
                    rcActionUseCases.getRCActionUseCase(rcActionId)?.also { rcAction ->
                        currentRCActionId = rcAction.id
                        _rcActionTitle.value = rcAction.title
                        _rcActionFrequency.value = rcAction.frequency
                        _rcActionCode.value = rcAction.code
                        _rcActionColor.value = rcAction.color
                    }
                }
            }

        }
    }

    fun onEvent(event: AddEditRCActionEvent) {
        when(event) {
            is AddEditRCActionEvent.SaveRCAction -> {
                viewModelScope.launch {
                    try {
                        rcActionUseCases.addRCActionUseCase(
                            RCAction(
                                title = event.title,
                                frequency = event.frequency,
                                code =  event.code,
                                color = Purple40.toArgb()
                            )
                        )

                        _eventFlow.emit(UIEvent.SaveRCAction)
                    } catch(e: InvalidRCActionException) {
                        // TODO: print error message
                    }
                }
            }
        }
    }

    sealed class UIEvent {
        object SaveRCAction: UIEvent()
    }
}
