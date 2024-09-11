package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.ChefProfileBO
import com.dreamsoftware.saborytv.domain.repository.IChefProfilesRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetChefProfilesUseCase(
    private val chefProfilesRepository: IChefProfilesRepository
) : FudgeTvUseCase<List<ChefProfileBO>>() {
    override suspend fun onExecuted():  List<ChefProfileBO> =
        chefProfilesRepository.getChefProfiles()
}