package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.UserPreferenceBO
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetUserPreferencesUseCase(
    private val userRepository: IUserRepository,
) : FudgeTvUseCase<UserPreferenceBO>() {

    override suspend fun onExecuted(): UserPreferenceBO =
        userRepository.getUserPreferences()
}