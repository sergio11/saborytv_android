package com.dreamsoftware.coins4all.data.remote.dto

import com.dreamsoftware.coins4all.data.local.entity.CoinDetailEntity
import com.dreamsoftware.coins4all.data.remote.response.*
import com.dreamsoftware.coins4all.domain.model.CoinDetail
import com.squareup.moshi.Json

data class CoinDetailDto(
    val id: String,
    val description: String?,
    @field:Json(name = "development_status")
    val developmentStatus: String?,
    @field:Json(name = "first_data_at")
    val firstDataAt: String?,
    @field:Json(name = "hardware_wallet")
    val hardwareWallet: Boolean?,
    @field:Json(name = "hash_algorithm")
    val hashAlgorithm: String?,
    @field:Json(name = "is_active")
    val isActive: Boolean,
    @field:Json(name = "is_new")
    val isNew: Boolean,
    @field:Json(name = "last_data_at")
    val lastDataAt: String?,
    val links: Links?,
    @field:Json(name = "links_extended")
    val linksExtended: List<LinksExtended>?,
    val message: String?,
    val name: String,
    @field:Json(name = "open_source")
    val openSource: Boolean?,
    @field:Json(name = "org_structure")
    val orgStructure: String?,
    @field:Json(name = "proof_type")
    val proofType: String?,
    val rank: Int,
    @field:Json(name = "started_at")
    val startedAt: String?,
    val symbol: String?,
    val tags: List<Tag>?,
    val team: List<Team>?,
    val type: String?,
    val whitepaper: Whitepaper?
)

val CoinDetailDto.coinDetail: CoinDetail
    get() = CoinDetail(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol ?: "",
        description = description ?: "",
        message = message ?: "",
        tags = tags?.map { it.name } ?: emptyList(),
        team = team ?: emptyList(),
        iconUrl = "https://static.coinpaprika.com/coin/$id/logo.png"
    )

val CoinDetailDto.coinDetailEntity: CoinDetailEntity
    get() = CoinDetailEntity(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol ?: "",
        description = description ?: "",
        message = message ?: "",
        tags = tags?.map { it.name } ?: emptyList(),
        team = team ?: emptyList(),
    )