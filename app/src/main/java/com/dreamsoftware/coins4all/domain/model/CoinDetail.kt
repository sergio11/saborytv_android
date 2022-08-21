package com.dreamsoftware.coins4all.domain.model

import com.dreamsoftware.coins4all.data.remote.response.Team

data class CoinDetail(
    val description: String,
    val id: String,
    val isActive: Boolean,
    val message: String,
    val name: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<Team>,
    val iconUrl: String
)
