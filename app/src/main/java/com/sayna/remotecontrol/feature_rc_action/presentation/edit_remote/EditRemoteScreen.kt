package com.sayna.remotecontrol.feature_rc_action.presentation.edit_remote

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import com.sayna.remotecontrol.feature_rc_action.presentation.components.DefaultButton
import com.sayna.remotecontrol.feature_rc_action.presentation.components.DefaultHeader
import com.sayna.remotecontrol.feature_rc_action.presentation.edit_remote.components.EditOptionsDialog
import com.sayna.remotecontrol.feature_rc_action.presentation.remote.RemoteViewModel
import com.sayna.remotecontrol.feature_rc_action.presentation.util.Screen
import com.sayna.remotecontrol.ui.theme.Purple40
import com.sayna.remotecontrol.ui.theme.PurpleGrey40
import com.sayna.remotecontrol.ui.theme.PurpleGrey80
import java.util.Collections

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditRemoteScreen(
    viewModel: EditRemoteViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value

    var testData: MutableList<RCAction> = mutableListOf(
        RCAction(
            title = "aloo5",
            frequency = 58000,
            code = "192 192 48 144 48 144 48 48 48 144 48 144 48 144 48 144 48 144 48 48 48 48 48 48 48 48 48 48 48 144 48 48 48 48 48 48 48 48 48 48 48 144 48 144 48 144 48 2534",
            color = Color.White.toArgb(),
        ),
        RCAction(
            title = "aloo6",
            frequency = 58000,
            code = "",
            color = Color.White.toArgb(),
        ),
        RCAction(
            title = "aloo7",
            frequency = 58000,
            code = "",
            color = Color.White.toArgb(),
        ),
        RCAction(
            title = "aloo8",
            frequency = 58000,
            code = "",
            color = Color.White.toArgb(),
        ),
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddRCActionScreen.route)
            },
                containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add action")
            }
        }
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(

            ) {
                DefaultHeader(text = "Edit Remote")
                
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ->
                    itemsIndexed(state.rcActions) { index, action ->
                        DefaultButton(
                            rcAction = action,
                            modifier = Modifier,
                            onClick = {
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun <E> MutableList<E>.move(fromIndex: Int, toIndex: Int) {
    if (fromIndex < toIndex) {
        for (i in fromIndex until toIndex) {
            Collections.swap(this, i, i + 1)
        }
    } else {
        for (i in fromIndex downTo toIndex + 1) {
            Collections.swap(this, i, i - 1)
        }
    }
}
