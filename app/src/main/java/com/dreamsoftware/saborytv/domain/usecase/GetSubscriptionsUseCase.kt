package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.SubscriptionBO
import com.dreamsoftware.saborytv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase

class GetSubscriptionsUseCase(
    private val subscriptionsRepository: ISubscriptionsRepository
) : FudgeTvUseCase<List<SubscriptionBO>>() {
    override suspend fun onExecuted(): List<SubscriptionBO> =
        subscriptionsRepository.getSubscriptions()
}