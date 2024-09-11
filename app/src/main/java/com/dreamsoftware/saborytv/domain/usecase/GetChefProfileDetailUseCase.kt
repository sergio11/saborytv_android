package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.ChefProfileBO
import com.dreamsoftware.saborytv.domain.repository.IChefProfilesRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class GetChefProfileDetailUseCase(
    private val chefProfilesRepository: IChefProfilesRepository
): FudgeTvUseCaseWithParams<GetChefProfileDetailUseCase.Params, ChefProfileBO>() {

    override suspend fun onExecuted(params: Params): ChefProfileBO =
        chefProfilesRepository.getChefProfileById(params.id)

    data class Params(
        val id: String
    )
}