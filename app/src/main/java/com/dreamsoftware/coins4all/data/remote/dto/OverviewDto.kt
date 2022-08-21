package com.dreamsoftware.coins4all.data.remote.dto

import com.dreamsoftware.coins4all.data.local.entity.OverviewEntity
import com.dreamsoftware.coins4all.domain.model.Overview
import com.squareup.moshi.Json

data class OverviewDto(
    @field:Json(name = "bitcoin_dominance_percentage")
    val bitcoinDominancePercentage: Double,
    @field:Json(name = "cryptocurrencies_number")
    val cryptocurrenciesNumber: Int,
    @field:Json(name = "last_updated")
    val lastUpdated: Int,
    @field:Json(name = "market_cap_ath_date")
    val marketCapAthDate: String,
    @field:Json(name = "market_cap_ath_value")
    val marketCapAthValue: Long,
    @field:Json(name = "market_cap_change_24h")
    val marketCapChange_24h: Double,
    @field:Json(name = "market_cap_usd")
    val marketCapUsd: Long,
    @field:Json(name = "volume_24h_ath_date")
    val volume24hAthDate: String,
    @field:Json(name = "volume_24h_ath_value")
    val volume24hAthValue: Long,
    @field:Json(name = "volume_24h_change_24h")
    val volume24hChange24h: Double,
    @field:Json(name = "volume_24h_percent_from_ath")
    val volume24hPercentFromAth: Int,
    @field:Json(name = "volume_24h_percent_to_ath")
    val volume24hPercentToAth: Double,
    @field:Json(name = "volume_24h_usd")
    val volume24hUsd: Long
)

val OverviewDto.overview
    get() = Overview(
        dominance = bitcoinDominancePercentage,
        cryptocurrenciesNumber = cryptocurrenciesNumber
    )

val OverviewDto.overviewEntity
    get() = OverviewEntity(
        bitcoinDominancePercentage,
        cryptocurrenciesNumber,
        lastUpdated,
        marketCapAthDate,
        marketCapAthValue,
        marketCapChange_24h,
        marketCapUsd,
        volume24hAthDate,
        volume24hAthValue,
        volume24hChange24h,
        volume24hPercentFromAth,
        volume24hPercentToAth,
        volume24hUsd
    )