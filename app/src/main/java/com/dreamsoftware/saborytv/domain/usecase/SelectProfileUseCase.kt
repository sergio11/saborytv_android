package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.ProfileBO
import com.dreamsoftware.saborytv.domain.repository.IProfilesRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class SelectProfileUseCase(
    private val profilesRepository: IProfilesRepository
): FudgeTvUseCaseWithParams<SelectProfileUseCase.Params, Unit>() {

    override suspend fun onExecuted(params: Params): Unit =
        profilesRepository.selectProfile(params.profile)

    data class Params(
        val profile: ProfileBO
    )
}