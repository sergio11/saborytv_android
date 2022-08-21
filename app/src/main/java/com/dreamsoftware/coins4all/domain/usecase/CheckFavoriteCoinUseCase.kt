package com.dreamsoftware.coins4all.domain.usecase

import com.dreamsoftware.coins4all.domain.repository.Coins4AllRepository
import javax.inject.Inject

class CheckFavoriteCoinUseCase @Inject constructor(
    private val repository: Coins4AllRepository
) {

    suspend operator fun invoke(id: String): Boolean {
        return repository.checkFavoriteCoin(id)
    }

}