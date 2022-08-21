package com.dreamsoftware.coins4all.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.dreamsoftware.coins4all.domain.model.CoinDetail
import com.dreamsoftware.coins4all.domain.repository.Coins4AllRepository
import com.dreamsoftware.coins4all.presentation.ui.utils.Action
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: Coins4AllRepository
) {
    operator fun invoke(id: String): Flow<Action<CoinDetail>> {
        return repository.getCoinById(id)
    }
}