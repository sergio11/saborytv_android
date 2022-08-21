package com.dreamsoftware.coins4all.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dreamsoftware.coins4all.data.local.dao.*
import com.dreamsoftware.coins4all.data.local.entity.*

@Database(
    entities = [
        CoinEntity::class,
        OverviewEntity::class,
        CoinDetailEntity::class,
        CoinCurrencyEntity::class,
        FavoriteCoinEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class Coins4AllDatabase : RoomDatabase() {

    abstract val coinDao: CoinDao
    abstract val overviewDao: OverviewDao
    abstract val coinDetailDao: CoinDetailDao
    abstract val coinCurrencyDao: CoinCurrencyDao
    abstract val favoriteCoinsDao: FavoriteCoinsDao

    companion object {

        const val DATABASE_NAME = "coins4All_database"

    }

}