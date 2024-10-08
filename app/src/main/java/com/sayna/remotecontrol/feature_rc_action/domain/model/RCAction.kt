package com.sayna.remotecontrol.feature_rc_action.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sayna.remotecontrol.ui.theme.BabyBlue
import com.sayna.remotecontrol.ui.theme.LightGreen
import com.sayna.remotecontrol.ui.theme.RedOrange
import com.sayna.remotecontrol.ui.theme.RedPink
import com.sayna.remotecontrol.ui.theme.Violet

@Entity
data class RCAction(
    val title: String,
    val frequency: Int,
    val code: String,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val rcActionColours = listOf(RedOrange, RedPink, BabyBlue, Violet, LightGreen)
    }
}

class InvalidRCActionException(message: String): Exception(message)
