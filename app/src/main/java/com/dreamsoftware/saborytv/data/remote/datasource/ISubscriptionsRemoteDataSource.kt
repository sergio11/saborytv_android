package com.dreamsoftware.saborytv.data.remote.datasource

import com.dreamsoftware.saborytv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.saborytv.data.remote.exception.FetchSubscriptionsRemoteException

interface ISubscriptionsRemoteDataSource {

    @Throws(FetchSubscriptionsRemoteException::class)
    suspend fun getSubscriptions(): List<SubscriptionDTO>
}