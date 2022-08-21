package com.dreamsoftware.coins4all.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dreamsoftware.coins4all.data.local.entity.CoinDetailEntity

@Dao
interface CoinDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinDetail(coinDetail: CoinDetailEntity)

    @Query("SELECT * FROM details WHERE id = :id")
    suspend fun getCoinDetail(id: String): CoinDetailEntity?

    @Query("DELETE FROM details")
    suspend fun deleteCoins()

}