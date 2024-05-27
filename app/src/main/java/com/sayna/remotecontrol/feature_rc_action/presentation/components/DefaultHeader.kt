package com.sayna.remotecontrol.feature_rc_action.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sayna.remotecontrol.ui.theme.Purple40

@Composable
fun DefaultHeader(
    text: String
) {
    Text(
        text = text,
        Modifier.padding(horizontal = 0.dp, vertical = 16.dp),
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        color = Purple40
    )
}