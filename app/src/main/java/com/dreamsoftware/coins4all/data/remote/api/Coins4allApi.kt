package com.dreamsoftware.coins4all.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.dreamsoftware.coins4all.data.remote.dto.CoinCurrencyDto
import com.dreamsoftware.coins4all.data.remote.dto.CoinDetailDto
import com.dreamsoftware.coins4all.data.remote.dto.CoinDto
import com.dreamsoftware.coins4all.data.remote.dto.OverviewDto

interface Coins4allApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{id}")
    suspend fun getCoinById(@Path("id") id: String): CoinDetailDto

    @GET("/v1/coins/{id}/ohlcv/historical?limit=365")
    suspend fun getHistoricalCurrencyForPeriod(
        @Path("id") id: String,
        @Query("start") start: String,
        @Query("end") end: String
    ): List<CoinCurrencyDto>

    @GET("https://api.coinpaprika.com/v1/global")
    suspend fun getOverview(): OverviewDto

}