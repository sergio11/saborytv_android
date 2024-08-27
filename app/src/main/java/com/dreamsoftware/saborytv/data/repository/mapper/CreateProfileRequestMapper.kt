package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.saborytv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class CreateProfileRequestMapper :
    IOneSideMapper<CreateProfileRequestBO, CreateProfileRequestDTO> {

    override fun mapInToOut(input: CreateProfileRequestBO): CreateProfileRequestDTO = with(input) {
        CreateProfileRequestDTO(
            uid = uid,
            alias = alias,
            pin = pin,
            avatarType = avatarType.name,
            userId = userId
        )
    }

    override fun mapInListToOutList(input: Iterable<CreateProfileRequestBO>): Iterable<CreateProfileRequestDTO> =
        input.map(::mapInToOut)
}