package com.dreamsoftware.saborytv.data.remote.datasource

import com.dreamsoftware.saborytv.data.remote.dto.request.AddUserSubscriptionDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.UserSubscriptionDTO
import com.dreamsoftware.saborytv.data.remote.exception.AddUserSubscriptionRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchUserSubscriptionRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.RemoveUserSubscriptionRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.VerifyHasActiveSubscriptionRemoteException

interface IUserSubscriptionsRemoteDataSource {

    @Throws(FetchUserSubscriptionRemoteException::class)
    suspend fun getUserSubscription(userId: String): UserSubscriptionDTO

    @Throws(VerifyHasActiveSubscriptionRemoteException::class)
    suspend fun hasActiveSubscription(userId: String): Boolean

    @Throws(AddUserSubscriptionRemoteException::class)
    suspend fun addUserSubscription(data: AddUserSubscriptionDTO): Boolean

    @Throws(RemoveUserSubscriptionRemoteException::class)
    suspend fun removeUserSubscription(userId: String): Boolean
}