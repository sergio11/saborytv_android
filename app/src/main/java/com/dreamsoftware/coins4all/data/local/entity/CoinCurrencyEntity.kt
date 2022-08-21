package com.dreamsoftware.coins4all.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dreamsoftware.coins4all.domain.model.CoinCurrency

@Entity
data class CoinCurrencyEntity(
    @PrimaryKey val id: String,
    val currency: List<CoinCurrency>
)
