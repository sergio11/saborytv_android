package com.dreamsoftware.coins4all.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.dreamsoftware.coins4all.data.local.dao.*
import com.dreamsoftware.coins4all.data.local.entity.CoinCurrencyEntity
import com.dreamsoftware.coins4all.data.local.entity.FavoriteCoinEntity
import com.dreamsoftware.coins4all.data.remote.api.Coins4allApi
import com.dreamsoftware.coins4all.data.remote.dto.*
import com.dreamsoftware.coins4all.domain.model.Coin
import com.dreamsoftware.coins4all.domain.model.CoinCurrency
import com.dreamsoftware.coins4all.domain.model.CoinDetail
import com.dreamsoftware.coins4all.domain.model.Overview
import com.dreamsoftware.coins4all.domain.repository.Coins4AllRepository
import com.dreamsoftware.coins4all.presentation.ui.utils.Action
import javax.inject.Inject

class Coins4AllRepositoryImpl @Inject constructor(
    private val api: Coins4allApi,
    private val coinDao: CoinDao,
    private val overviewDao: OverviewDao,
    private val coinDetailDao: CoinDetailDao,
    private val coinCurrencyDao: CoinCurrencyDao,
    private val favoriteCoinsDao: FavoriteCoinsDao
) : Coins4AllRepository {

    override fun getCoins(): Flow<Action<List<Coin>>> = flow {

        emit(Action.Loading())
        val coins = coinDao.getCoins().map { it.coin }

        try {
            val remoteCoins = api.getCoins()
            emit(
                Action.Success(
                    remoteCoins.map { it.coin }
                )
            )
            coinDao.insertCoins(remoteCoins.map { it.coinEntity })
        } catch (e: Exception) {
            emit(Action.Empty(e.localizedMessage))
            if (coins.isNotEmpty()) emit(Action.Success(coins))
        }
    }

    override fun getCoinById(id: String): Flow<Action<CoinDetail>> = flow {
        emit(Action.Loading())
        val coinDetail = coinDetailDao.getCoinDetail(id)

        try {
            val coin = api.getCoinById(id)
            emit(Action.Success(coin.coinDetail))
            coinDetailDao.insertCoinDetail(coin.coinDetailEntity)
        } catch (e: Exception) {
            emit(Action.Empty(e.localizedMessage))
            coinDetail?.let {
                emit(Action.Success(coinDetail.coinDetail))
            }
        }
    }

    override fun getHistoricalCurrencyForPeriod(
        id: String,
        start: String,
        end: String
    ): Flow<Action<List<CoinCurrency>>> = flow {
        emit(Action.Loading())
        val coinCurrency = coinCurrencyDao.getCoinCurrency(id)

        try {
            val remoteCurrency =
                api.getHistoricalCurrencyForPeriod(id, start, end).map { it.coinCurrency }
            emit(Action.Success(remoteCurrency))
            coinCurrencyDao.insertCoinCurrency(
                CoinCurrencyEntity(
                    id, remoteCurrency
                )
            )
        } catch (e: Exception) {
            emit(Action.Empty(e.localizedMessage))
            coinCurrency?.let {
                emit(Action.Success(coinCurrency.currency))
            }
        }

    }

    override fun getOverview(): Flow<Action<Overview>> = flow {
        emit(Action.Loading())
        val overview = overviewDao.getOverview()

        try {
            val overviewDto = api.getOverview()
            emit(Action.Success(overviewDto.overview))
            overviewDao.insertOverview(overviewDto.overviewEntity)
        } catch (e: Exception) {
            emit(Action.Empty(e.localizedMessage))
            overview?.let {
                emit(Action.Success(overview.overview))
            }
        }
    }

    override fun getFavoriteCoins(): Flow<Action<List<Coin>>> = flow {

        favoriteCoinsDao.getCoins().collect { list ->
            emit(Action.Loading())

            val coinIds = list.map { it.id }
            val coinList: List<Coin> = coinIds.mapNotNull { id ->
                val shadowCoin = try {
                    api.getCoinById(id).coinDetail
                } catch (e: Exception) {
                    coinDetailDao.getCoinDetail(id)?.coinDetail
                }

                shadowCoin?.let {
                    Coin(
                        id = id,
                        isActive = shadowCoin.isActive,
                        isNew = false,
                        name = shadowCoin.name,
                        symbol = shadowCoin.symbol,
                        rank = shadowCoin.rank,
                        iconUrl = "https://static.coinpaprika.com/coin/$id/logo.png"
                    )
                }
            }
            if (coinList.isNotEmpty()) emit(Action.Success(coinList))
            else emit(Action.Empty("Couldn't reach server, or your favorite coins are not cached, or they are not exist"))
        }

    }

    override suspend fun checkFavoriteCoin(id: String): Boolean {
        return favoriteCoinsDao.getFavoriteCoin(id)?.let { true } ?: false
    }

    override suspend fun insertFavoriteCoin(id: String) {
        favoriteCoinsDao.insertCoin(FavoriteCoinEntity(id))
    }

    override suspend fun removeFavoriteCoin(id: String) {
        favoriteCoinsDao.removeFavoriteCoin(FavoriteCoinEntity(id))
    }


}