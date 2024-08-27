package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.request.CreateUserDTO
import com.dreamsoftware.saborytv.domain.model.SignUpBO
import com.dreamsoftware.saborytv.ui.utils.EMPTY
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class CreateUserMapper : IOneSideMapper<SignUpBO, CreateUserDTO> {

    override fun mapInToOut(input: SignUpBO): CreateUserDTO = with(input) {
        CreateUserDTO(
            uid = String.EMPTY,
            email = email,
            username = username,
            firstName = firstName,
            lastName = lastName
        )
    }

    override fun mapInListToOutList(input: Iterable<SignUpBO>): Iterable<CreateUserDTO> =
        input.map(::mapInToOut)
}