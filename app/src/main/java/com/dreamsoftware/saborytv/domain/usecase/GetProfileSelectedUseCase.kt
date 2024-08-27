package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.ProfileBO
import com.dreamsoftware.saborytv.domain.repository.IProfilesRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetProfileSelectedUseCase(
    private val userRepository: IUserRepository,
    private val profilesRepository: IProfilesRepository
): FudgeTvUseCase<ProfileBO>() {
    override suspend fun onExecuted(): ProfileBO =
        userRepository.getAuthenticatedUid()
            .let { profilesRepository.getProfileSelectedByUser(it) }
}