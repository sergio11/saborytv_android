package com.dreamsoftware.coins4all.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.dreamsoftware.coins4all.data.local.entity.CoinEntity

@Dao
interface CoinDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertCoins(coin: List<CoinEntity>)

    @Query("SELECT * FROM coins")
    suspend fun getCoins(): List<CoinEntity>

    @Query("DELETE FROM coins")
    suspend fun deleteCoins()

}