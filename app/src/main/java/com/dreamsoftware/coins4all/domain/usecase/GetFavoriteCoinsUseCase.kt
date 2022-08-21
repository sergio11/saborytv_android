package com.dreamsoftware.coins4all.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.dreamsoftware.coins4all.domain.model.Coin
import com.dreamsoftware.coins4all.domain.repository.Coins4AllRepository
import com.dreamsoftware.coins4all.presentation.ui.utils.Action
import javax.inject.Inject

class GetFavoriteCoinsUseCase @Inject constructor(
    private val repository: Coins4AllRepository
) {

    operator fun invoke(): Flow<Action<List<Coin>>> {
        return repository.getFavoriteCoins()
    }

}