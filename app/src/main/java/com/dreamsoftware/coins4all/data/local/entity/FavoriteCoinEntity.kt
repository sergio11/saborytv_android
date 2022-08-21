package com.dreamsoftware.coins4all.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCoinEntity(
    @PrimaryKey val id: String
)