package com.sayna.remotecontrol.feature_rc_action.presentation.add_rcaction

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sayna.remotecontrol.feature_rc_action.presentation.components.DefaultHeader
import com.sayna.remotecontrol.feature_rc_action.presentation.util.Screen
import com.sayna.remotecontrol.ui.theme.Red

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddRCActionScreen(
    editing: Boolean = false,
    id: Int = -1,
    viewModel: AddRCActionViewModel = hiltViewModel(),
    navController: NavController
) {
    var title by remember { mutableStateOf(viewModel.rcActionTitle.value) }
    var frequency by remember { mutableStateOf(viewModel.rcActionFrequency.value.toString()) }
    var code by remember { mutableStateOf(viewModel.rcActionCode.value) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.EditRemoteScreen.route)

                // save new rc action
                // try to convert frequency to int
                var intFreq = 0
                try {
                    intFreq = frequency.toInt()
                } catch(e: Exception) {

                }

                viewModel.onEvent(AddEditRCActionEvent.SaveRCAction(
                    title = title,
                    frequency = intFreq,
                    code = code
                ))
            },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Add action")
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                if(editing)
                {
                    DefaultHeader(text = "Edit Remote Control Action")
                }
                else
                {
                    DefaultHeader(text = "Add Remote Control Action")
                }

                OutlinedTextField(
                    value = viewModel.rcActionTitle.value,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = viewModel.rcActionFrequency.value.toString(),
                    onValueChange = { frequency = it },
                    label = { Text("Frequency") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = viewModel.rcActionCode.value,
                    onValueChange = { code = it },
                    label = { Text("code") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                if(editing)
                {
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.EditRemoteScreen.route)
                                viewModel.onEvent(AddEditRCActionEvent.DeleteRCAction)
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete remote control action",
                            tint = Red
                        )

                        Text(
                            text = "Delete remote control action",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 16.sp,
                            color = Red
                        )
                    }

                }
            }
        }
    }
}