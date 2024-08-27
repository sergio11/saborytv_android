package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class RemoveUserSubscriptionUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository
) : FudgeTvUseCase<Boolean>() {

    override suspend fun onExecuted(): Boolean {
        val userId = userRepository.getAuthenticatedUid()
        return subscriptionsRepository.removeUserSubscription(userId)
    }
}