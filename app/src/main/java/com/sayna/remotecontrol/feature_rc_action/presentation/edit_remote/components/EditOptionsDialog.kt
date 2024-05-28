package com.sayna.remotecontrol.feature_rc_action.presentation.edit_remote.components

import android.widget.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.sayna.remotecontrol.feature_rc_action.presentation.edit_remote.EditRemoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditOptionsDialog(
    viewModel: EditRemoteViewModel = hiltViewModel()
) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
    ) {
        Column {
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Delete")
            }
        }
    }
}