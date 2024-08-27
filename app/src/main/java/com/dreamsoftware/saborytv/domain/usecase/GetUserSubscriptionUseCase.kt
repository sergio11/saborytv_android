package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.UserSubscriptionBO
import com.dreamsoftware.saborytv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetUserSubscriptionUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository
) : FudgeTvUseCase<UserSubscriptionBO>() {

    override suspend fun onExecuted(): UserSubscriptionBO {
        val userId = userRepository.getAuthenticatedUid()
        return subscriptionsRepository.getUserSubscription(userId)
    }
}