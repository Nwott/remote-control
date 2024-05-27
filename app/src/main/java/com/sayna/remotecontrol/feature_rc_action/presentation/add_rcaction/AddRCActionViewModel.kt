package com.sayna.remotecontrol.feature_rc_action.presentation.add_rcaction

import androidx.compose.ui.graphics.toArgb
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
): ViewModel() {

    private var currentNoteId: Int? = null

    fun onEvent(event: AddRCActionEvent) {
        when(event) {
            is AddRCActionEvent.SaveRCAction -> {
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
