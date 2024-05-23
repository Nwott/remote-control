package com.sayna.remotecontrol.feature_rc_action.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RCActionDatabase::class],
    version = 1
)
abstract class RCActionDatabase : RoomDatabase() {
    abstract val rcDao: RCDao

    companion object {
        const val DATABASE_NAME = "rc_actions_db"
    }
}