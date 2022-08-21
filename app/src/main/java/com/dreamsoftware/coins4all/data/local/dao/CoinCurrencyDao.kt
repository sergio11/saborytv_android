package com.dreamsoftware.coins4all.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dreamsoftware.coins4all.data.local.entity.CoinCurrencyEntity

@Dao
interface CoinCurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinCurrency(coin: CoinCurrencyEntity)

    @Query("SELECT * FROM coincurrencyentity WHERE id = :id")
    suspend fun getCoinCurrency(id: String): CoinCurrencyEntity?

    @Query("DELETE FROM coincurrencyentity")
    suspend fun deleteCoins()

}