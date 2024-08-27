package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class SignOffUseCase(
    private val userRepository: IUserRepository,
): FudgeTvUseCase<Unit>() {
    override suspend fun onExecuted() {
        userRepository.signOff()
    }
}