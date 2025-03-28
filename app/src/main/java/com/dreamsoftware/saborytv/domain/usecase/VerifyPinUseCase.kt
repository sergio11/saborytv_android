package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.repository.IProfilesRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class VerifyPinUseCase(
    private val profilesRepository: IProfilesRepository
): FudgeTvUseCaseWithParams<VerifyPinUseCase.Params, Unit>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        profilesRepository.verifyPin(profileId, pin)
    }

    data class Params(
        val profileId: String,
        val pin: Int
    )
}