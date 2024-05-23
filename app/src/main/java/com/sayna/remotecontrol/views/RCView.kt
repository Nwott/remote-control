package com.sayna.remotecontrol.views

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sayna.remotecontrol.ui.theme.*;

class RCView {
    @Preview
    @Composable
    fun RemoteControlButton() {
        val size = 100.dp;
        val textSize = 20.sp;
        val borderRadius = 20;
        ElevatedButton(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(borderRadius),
            modifier = Modifier.size(size)
        ) {
            Text(
                text = "Test",
                color = Color.Black,
                fontSize = textSize
            )
        }
    }
}