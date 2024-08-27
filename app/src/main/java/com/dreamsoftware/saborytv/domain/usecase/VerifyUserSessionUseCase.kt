package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class VerifyUserSessionUseCase(
    private val userRepository: IUserRepository
): FudgeTvUseCase<Boolean>() {

    override suspend fun onExecuted(): Boolean = runCatching { userRepository.hasSession() }
        .getOrDefault(false)
}