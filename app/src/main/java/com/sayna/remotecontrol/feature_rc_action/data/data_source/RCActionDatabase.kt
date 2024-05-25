package com.sayna.remotecontrol.feature_rc_action.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction

@Database(
    entities = [RCAction::class],
    version = 2,
    exportSchema = false
)
abstract class RCActionDatabase : RoomDatabase() {
    abstract val rcActionDao: RCActionDao

    companion object {
        const val DATABASE_NAME = "rc_actions_db"
    }
}