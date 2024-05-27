package com.sayna.remotecontrol.feature_rc_action.presentation.remote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import com.sayna.remotecontrol.feature_rc_action.presentation.components.DefaultButton
import com.sayna.remotecontrol.feature_rc_action.presentation.components.DefaultHeader

@Composable
fun RemoteScreen(
    viewModel: RemoteViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    var testData: MutableList<RCAction> = mutableListOf(
        RCAction(
            title = "aloo",
            frequency = 58000,
            code = "192 192 48 144 48 144 48 48 48 144 48 144 48 144 48 144 48 144 48 48 48 48 48 48 48 48 48 48 48 144 48 48 48 48 48 48 48 48 48 48 48 144 48 144 48 144 48 2534",
            color = Color.White.toArgb(),
        ),
        RCAction(
            title = "aloo2",
            frequency = 58000,
            code = "",
            color = Color.White.toArgb(),
        ),
        RCAction(
            title = "aloo3",
            frequency = 58000,
            code = "",
            color = Color.White.toArgb(),
        ),
        RCAction(
            title = "aloo4",
            frequency = 58000,
            code = "",
            color = Color.White.toArgb(),
        ),
    )

    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            DefaultHeader(text = "Remote")

            // grid that has buttons for each RC action
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(testData) {rcAction ->
                    DefaultButton(
                        rcAction = rcAction,
                        onClick = { viewModel.OnEvent(RemoteEvent.PerformRCAction(rcAction)) }
                    )
                }
            }
        }
    }
}