package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.request.UpdatedUserRequestDTO
import com.dreamsoftware.saborytv.domain.model.UpdatedUserRequestBO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class UpdatedUserRequestMapper :
    IOneSideMapper<UpdatedUserRequestBO, UpdatedUserRequestDTO> {

    override fun mapInToOut(input: UpdatedUserRequestBO): UpdatedUserRequestDTO = with(input) {
        UpdatedUserRequestDTO(
            firstName = firstName,
            lastName = lastName,
            username = username
        )
    }

    override fun mapInListToOutList(input: Iterable<UpdatedUserRequestBO>): Iterable<UpdatedUserRequestDTO> =
        input.map(::mapInToOut)
}