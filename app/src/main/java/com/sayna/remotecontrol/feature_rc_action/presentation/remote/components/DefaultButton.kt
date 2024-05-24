package com.sayna.remotecontrol.feature_rc_action.presentation.remote.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    text: String = "Button",
    description: String = "Button description",
    textStyle: TextStyle = TextStyle(),
    icon: ImageVector? = null
) {
    ElevatedButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .size(50.dp),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(1.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(icon != null)
            {
                Icon(
                    icon,
                    contentDescription = description
                )

                Text(
                    text = text,
                    style = textStyle
                )
            }
            else
            {
                Text(
                    text = text,
                    style = textStyle
                )
            }
        }
    }
}

@Preview
@Composable
fun TestDefaultButton()
{
    DefaultButton(
        textStyle = MaterialTheme.typography.bodySmall
    )
}

@Preview
@Composable
fun TestDefaultIconButton()
{
    DefaultButton(
        icon = Icons.Default.PowerSettingsNew,
        textStyle = MaterialTheme.typography.bodySmall
    )
}