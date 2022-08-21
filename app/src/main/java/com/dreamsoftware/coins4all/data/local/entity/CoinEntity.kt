package com.dreamsoftware.coins4all.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dreamsoftware.coins4all.domain.model.Coin

@Entity(tableName = "coins")
data class CoinEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "is_active")
    val isActive: Boolean,
    @ColumnInfo(name = "is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
) {
    val coin
        get() = Coin(
            id = id,
            isActive = isActive,
            isNew = isNew,
            name = name,
            rank = rank,
            symbol = symbol,
            iconUrl = "https://static.coinpaprika.com/coin/$id/logo.png"
        )
}
