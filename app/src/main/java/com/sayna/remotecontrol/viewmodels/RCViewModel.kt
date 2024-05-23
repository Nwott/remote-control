package com.sayna.remotecontrol.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sayna.remotecontrol.models.RCAction
import com.sayna.remotecontrol.repositories.RCActionRepository
import kotlinx.coroutines.launch

class RCViewModel  : ViewModel() {
    private val repository = RCActionRepository()

    private val _rcActions = MutableLiveData<List<RCAction>?>()
    val rcActions: LiveData<List<RCAction>?> = _rcActions;

    fun FetchRCActions() {
        viewModelScope.launch {
            try {
                val actions = repository.GetRemoteControlActions();
                _rcActions.value = actions;
            }
            catch(e: Exception)
            {
                // handle error
            }
        }
    }
}