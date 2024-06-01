package com.sayna.remotecontrol.feature_rc_action.presentation.add_rcaction

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.core.net.toFile
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayna.remotecontrol.feature_rc_action.domain.model.InvalidRCActionException
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import com.sayna.remotecontrol.feature_rc_action.domain.use_case.RCActionUseCases
import com.sayna.remotecontrol.ui.theme.Purple40
import com.sayna.remotecontrol.ui.theme.RedPink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

@HiltViewModel
class AddRCActionViewModel @Inject constructor(
    private val rcActionUseCases: RCActionUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    // states of rc action data
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
            is AddEditRCActionEvent.DeleteRCAction -> {
                viewModelScope.launch {
                    if(currentRCActionId != -1 && currentRCActionId != null) {
                        val action = rcActionUseCases.getRCActionUseCase(currentRCActionId!!)

                        if(action != null) {
                            rcActionUseCases.deleteRCActionUseCase(action)
                        }

                    }
                }
            }
            is AddEditRCActionEvent.ImportRCActions -> {
                viewModelScope.launch {
                    // parse file and create rcActions
                    ParseFile(event.uri)
                }
            }
        }
    }

    private suspend fun ParseFile(uri: Uri) {
        try {
            val file = uri.toFile()
            val fileIn = withContext(Dispatchers.IO) {
                FileInputStream(file)
            }
            val reader = BufferedReader(InputStreamReader(fileIn))

            // current rcAction
            var title: String = ""
            var frequency: Int = -1
            var code: String = ""

            // loops until we reach end of file
            while(true)
            {
                val line = withContext(Dispatchers.IO) {
                    reader.readLine()
                }

                // stopping condition
                if(line.equals("EOF")) {
                    break
                }

                title = line

                frequency = withContext(Dispatchers.IO) {
                    reader.readLine()
                }.toInt()

                code = withContext(Dispatchers.IO) {
                    reader.readLine()
                }

                rcActionUseCases.addRCActionUseCase(
                    rcAction = RCAction(
                        title = title,
                        frequency = frequency,
                        code = code,
                        color = Purple40.toArgb()
                    )
                )
            }
        }
        catch(e: FileNotFoundException) {
            println("Cannot find file")
        }
        catch(e: IOException) {
            println("IO Exception")
        }

    }

    sealed class UIEvent {
        object SaveRCAction: UIEvent()
    }
}
