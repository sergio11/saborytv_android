package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.response.ChefProfileDTO
import com.dreamsoftware.saborytv.domain.model.ChefProfileBO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class ChefProfileMapper : IOneSideMapper<ChefProfileDTO, ChefProfileBO> {

    override fun mapInToOut(input: ChefProfileDTO): ChefProfileBO = with(input) {
        ChefProfileBO(
            id = id,
            name = name,
            description = description,
            imageUrl = imageUrl
        )
    }

    override fun mapInListToOutList(input: Iterable<ChefProfileDTO>): Iterable<ChefProfileBO> =
        input.map(::mapInToOut)
}