package com.dreamsoftware.coins4all.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dreamsoftware.coins4all.data.remote.response.Team
import com.dreamsoftware.coins4all.domain.model.CoinDetail

@Entity(tableName = "details")
data class CoinDetailEntity(
    @PrimaryKey
    val id: String,
    val description: String,
    @ColumnInfo(name = "is_active")
    val isActive: Boolean,
    val message: String,
    @NonNull
    val name: String,
    val rank: Int,
    @NonNull
    val symbol: String,
    val tags: List<String>,
    val team: List<Team>
) {
    val coinDetail
        get() = CoinDetail(
            id = id,
            isActive = isActive,
            name = name,
            rank = rank,
            symbol = symbol,
            description = description,
            message = message,
            tags = tags,
            team = team,
            iconUrl = "https://static.coinpaprika.com/coin/$id/logo.png"
        )
}
