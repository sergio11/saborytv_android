package com.dreamsoftware.coins4all.data.remote.dto

import com.dreamsoftware.coins4all.data.local.entity.CoinEntity
import com.dreamsoftware.coins4all.domain.model.Coin
import com.squareup.moshi.Json

data class CoinDto(
    val id: String,
    @field:Json(name = "is_active")
    val isActive: Boolean,
    @field:Json(name = "is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

val CoinDto.coin: Coin
    get() = Coin(
        id = id,
        isActive = isActive,
        isNew = isNew,
        name = name,
        rank = rank,
        symbol = symbol,
        iconUrl = "https://static.coinpaprika.com/coin/$id/logo.png"
    )

val CoinDto.coinEntity: CoinEntity
    get() = CoinEntity(
        id = id,
        isActive = isActive,
        isNew = isNew,
        name = name,
        rank = rank,
        symbol = symbol,
        type = type
    )
