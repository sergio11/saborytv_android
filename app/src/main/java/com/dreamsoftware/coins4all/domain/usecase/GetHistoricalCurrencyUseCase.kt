package com.dreamsoftware.coins4all.domain.usecase

import kotlinx.coroutines.flow.Flow
import com.dreamsoftware.coins4all.domain.model.CoinCurrency
import com.dreamsoftware.coins4all.domain.repository.Coins4AllRepository
import com.dreamsoftware.coins4all.presentation.ui.utils.Action
import javax.inject.Inject

class GetHistoricalCurrencyUseCase @Inject constructor(
    private val repository: Coins4AllRepository
) {
    operator fun invoke(id: String, start: String, end: String): Flow<Action<List<CoinCurrency>>> {
        return repository.getHistoricalCurrencyForPeriod(id, start, end)
    }
}