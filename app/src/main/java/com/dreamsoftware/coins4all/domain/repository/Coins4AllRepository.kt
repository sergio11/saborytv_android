package com.dreamsoftware.coins4all.domain.repository

import kotlinx.coroutines.flow.Flow
import com.dreamsoftware.coins4all.domain.model.Coin
import com.dreamsoftware.coins4all.domain.model.CoinCurrency
import com.dreamsoftware.coins4all.domain.model.CoinDetail
import com.dreamsoftware.coins4all.domain.model.Overview
import com.dreamsoftware.coins4all.presentation.ui.utils.Action

interface Coins4AllRepository {

    fun getCoins(): Flow<Action<List<Coin>>>

    fun getCoinById(id: String): Flow<Action<CoinDetail>>

    fun getHistoricalCurrencyForPeriod(
        id: String,
        start: String,
        end: String
    ): Flow<Action<List<CoinCurrency>>>

    fun getOverview(): Flow<Action<Overview>>

    fun getFavoriteCoins(): Flow<Action<List<Coin>>>

    suspend fun checkFavoriteCoin(id: String): Boolean

    suspend fun insertFavoriteCoin(id: String)

    suspend fun removeFavoriteCoin(id: String)

}