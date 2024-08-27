package com.dreamsoftware.saborytv.domain.repository

import com.dreamsoftware.saborytv.domain.exception.AddUserSubscriptionException
import com.dreamsoftware.saborytv.domain.exception.FetchSubscriptionsException
import com.dreamsoftware.saborytv.domain.exception.FetchUserSubscriptionException
import com.dreamsoftware.saborytv.domain.exception.RemoveUserSubscriptionException
import com.dreamsoftware.saborytv.domain.exception.VerifyHasActiveSubscriptionException
import com.dreamsoftware.saborytv.domain.model.AddUserSubscriptionBO
import com.dreamsoftware.saborytv.domain.model.SubscriptionBO
import com.dreamsoftware.saborytv.domain.model.UserSubscriptionBO

interface ISubscriptionsRepository {

    @Throws(FetchSubscriptionsException::class)
    suspend fun getSubscriptions(): List<SubscriptionBO>

    @Throws(FetchUserSubscriptionException::class)
    suspend fun getUserSubscription(userId: String): UserSubscriptionBO

    @Throws(VerifyHasActiveSubscriptionException::class)
    suspend fun hasActiveSubscription(userId: String): Boolean

    @Throws(AddUserSubscriptionException::class)
    suspend fun addUserSubscription(data: AddUserSubscriptionBO): Boolean

    @Throws(RemoveUserSubscriptionException::class)
    suspend fun removeUserSubscription(userId: String): Boolean
}