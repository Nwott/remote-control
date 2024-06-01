package com.sayna.remotecontrol.feature_rc_action.presentation.add_rcaction

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sayna.remotecontrol.feature_rc_action.presentation.components.DefaultButton
import com.sayna.remotecontrol.feature_rc_action.presentation.components.DefaultHeader
import com.sayna.remotecontrol.feature_rc_action.presentation.components.DefaultOutlinedButton
import com.sayna.remotecontrol.feature_rc_action.presentation.util.Screen
import com.sayna.remotecontrol.ui.theme.Darker
import com.sayna.remotecontrol.ui.theme.PrimaryPurple
import com.sayna.remotecontrol.ui.theme.Purple40
import com.sayna.remotecontrol.ui.theme.Red
import com.sayna.remotecontrol.ui.theme.SelectedPurple

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddRCActionScreen(
    editing: Boolean = false,
    id: Int = -1,
    viewModel: AddRCActionViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current

    var title by remember  { mutableStateOf("") }
    var frequency by remember { mutableStateOf("0") }
    var code by remember { mutableStateOf("0") }

    if(editing) {
        title = viewModel.rcActionTitle.value
        frequency = viewModel.rcActionFrequency.value.toString()
        code = viewModel.rcActionCode.value
    }

    var screen by remember { mutableStateOf("single") }
    
    // handles opening file explorer
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
         uri?.let {

         }
    }

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
            Column() {

                Column {
                    if(editing)
                    {
                        DefaultHeader(text = "Edit Remote Control Action")
                    }
                    else
                    {
                        DefaultHeader(text = "Add Remote Control Action")
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(20.dp))
                            .background(Darker)
                            .padding(vertical = 2.dp, horizontal = 5.dp),
                        horizontalArrangement = Arrangement.Center,

                    ) {
                        Button(
                            onClick = { screen = "single" },
                            modifier = Modifier.weight(0.5f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if(screen == "single") Purple40 else Darker
                            )
                        ) {
                            Text(
                                text = "Single",
                                color = if(screen == "single") Color.White else Purple40
                            )
                        }

                        Button(
                            onClick = { screen = "import" },
                            modifier = Modifier.weight(0.5f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if(screen == "import") Purple40 else Darker
                            )
                        ) {
                            Text(
                                text = "Import",
                                color = if(screen == "import") Color.White else Purple40
                            )
                        }
                    }

                    if(screen == "single")
                    {
                        OutlinedTextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("Title") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = frequency,
                            onValueChange = { frequency = it },
                            label = { Text("Frequency") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = code,
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
                    else if(screen == "import")
                    {
                        Column {
                            Spacer(modifier = Modifier.height(16.dp))

                            ElevatedButton(
                                onClick = {
                                    launcher.launch("text/plain")
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Import From File",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}