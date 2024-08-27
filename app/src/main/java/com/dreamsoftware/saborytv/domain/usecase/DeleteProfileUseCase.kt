package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.repository.IProfilesRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class DeleteProfileUseCase(
    private val profileRepository: IProfilesRepository
): FudgeTvUseCaseWithParams<DeleteProfileUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params): Boolean =
        profileRepository.deleteProfile(params.profileId)

    data class Params(
        val profileId: String
    )
}