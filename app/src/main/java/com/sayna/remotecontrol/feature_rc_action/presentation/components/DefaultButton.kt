package com.sayna.remotecontrol.feature_rc_action.presentation.components

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import kotlin.math.min

@Composable
fun DefaultButton(
    rcAction: RCAction? = null,
    description: String = "Button description",
    textStyle: TextStyle = TextStyle(),
    icon: ImageVector? = null,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val btnText: String = rcAction?.title ?: "No title"
    var buttonSize by remember { mutableStateOf(Size.Zero) }

    ElevatedButton(
        onClick = { onClick() },
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                buttonSize = coordinates.size.toSize()
            }
            .aspectRatio(1f),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(1.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val fontSize = CalculateFontSize(buttonSize)
            val iconSize = CalculateIconSize(buttonSize)

            if(icon != null)
            {
                Icon(
                    icon,
                    modifier = Modifier
                        .size(iconSize),
                    contentDescription = description
                )

                Text(
                    text = btnText,
                    style = textStyle,
                    fontSize = fontSize
                )
            }
            else
            {
                Text(
                    text = btnText,
                    style = textStyle,
                    fontSize = fontSize
                )
            }
        }
    }
}

@Composable
fun CalculateFontSize(buttonSize: Size): TextUnit {
    val scaleFactor = 0.05f
    val minDimension = min(buttonSize.width, buttonSize.height)
    return (minDimension * scaleFactor).sp
}

@Composable
fun CalculateIconSize(buttonSize: Size): Dp {
    val scaleFactor = 0.1f
    val minDimension = min(buttonSize.width, buttonSize.height)
    return (minDimension * scaleFactor).dp
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