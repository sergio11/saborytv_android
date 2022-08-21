package com.dreamsoftware.coins4all.domain.usecase

import com.dreamsoftware.coins4all.domain.repository.Coins4AllRepository
import javax.inject.Inject

class AddFavoriteCoinUseCase @Inject constructor(
    private val repository: Coins4AllRepository
) {

    suspend operator fun invoke(id: String) {
        repository.insertFavoriteCoin(id)
    }

}