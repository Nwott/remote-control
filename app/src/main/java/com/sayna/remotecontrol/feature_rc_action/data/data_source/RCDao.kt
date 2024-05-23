package com.sayna.remotecontrol.feature_rc_action.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import kotlinx.coroutines.flow.Flow

@Dao
interface RCDao {
    @Query("SELECT * FROM RCAction")
    fun GetRCActions(): Flow<List<RCAction>>

    @Query("SELECT * FROM RCAction WHERE id = :id")
    suspend fun GetRCActionById(id: Int) : RCAction?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertRCAction(rcAction: RCAction)

    @Delete
    suspend fun DeleteRCAction(rcAction: RCAction)
}