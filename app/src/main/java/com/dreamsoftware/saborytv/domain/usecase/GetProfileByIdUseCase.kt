package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.ProfileBO
import com.dreamsoftware.saborytv.domain.repository.IProfilesRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class GetProfileByIdUseCase(
    private val profilesRepository: IProfilesRepository
): FudgeTvUseCaseWithParams<GetProfileByIdUseCase.Params, ProfileBO>() {

    override suspend fun onExecuted(params: Params): ProfileBO =
        profilesRepository.getProfileById(params.profileId)

    data class Params(
        val profileId: String
    )
}