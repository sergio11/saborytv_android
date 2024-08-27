package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.response.SubscriptionDTO
import com.dreamsoftware.saborytv.domain.model.SubscriptionBO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class SubscriptionMapper : IOneSideMapper<SubscriptionDTO, SubscriptionBO> {

    override fun mapInToOut(input: SubscriptionDTO): SubscriptionBO = with(input) {
        SubscriptionBO(
            id = id,
            periodTime = periodTime,
            price = price
        )
    }

    override fun mapInListToOutList(input: Iterable<SubscriptionDTO>): Iterable<SubscriptionBO> =
        input.map(::mapInToOut)
}