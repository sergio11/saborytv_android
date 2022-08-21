package com.dreamsoftware.coins4all.data.remote.dto

import com.dreamsoftware.coins4all.domain.model.CoinCurrency
import com.squareup.moshi.Json

data class CoinCurrencyDto(
    val close: Double,
    val high: Double,
    val low: Double,
    @field:Json(name = "market_cap")
    val marketCap: Long,
    val open: Double,
    @field:Json(name = "time_close")
    val timeClose: String,
    @field:Json(name = "time_open")
    val timeOpen: String,
    val volume: Long
)

val CoinCurrencyDto.coinCurrency
    get() = CoinCurrency(
        currency = open
    )