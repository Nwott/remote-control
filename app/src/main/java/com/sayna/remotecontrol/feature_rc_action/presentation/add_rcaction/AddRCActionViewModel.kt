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
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private var currentRCActionId : Int? = null

    init {
        savedStateHandle.get<Int>("rcActionId")?.let { rcActionId->
            if(rcActionId != -1) {
                viewModelScope.launch {
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
                    } catch(e: InvalidRCActionException) {
                        // TODO: print error message
                    }
                }
            }
        }
    }
}
