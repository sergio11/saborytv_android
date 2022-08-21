package com.dreamsoftware.coins4all.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dreamsoftware.coins4all.data.local.entity.OverviewEntity

@Dao
interface OverviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOverview(overview: OverviewEntity)

    @Query("SELECT * FROM overviewentity")
    suspend fun getOverview(): OverviewEntity?

    @Query("DELETE FROM overviewentity")
    suspend fun deleteCoins()

}