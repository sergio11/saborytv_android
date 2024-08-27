package com.dreamsoftware.saborytv.ui.screens.subscription

import com.dreamsoftware.saborytv.domain.model.SubscriptionBO
import com.dreamsoftware.fudge.core.IFudgeTvScreenActionListener

interface ISubscriptionScreenActionListener: IFudgeTvScreenActionListener {
    fun onSubscriptionOptionUpdated(subscription: SubscriptionBO)
    fun onSubscribe()
    fun onRestorePurchases()
    fun onCompleted()
}