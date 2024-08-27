package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.response.UserResponseDTO
import com.dreamsoftware.saborytv.domain.model.UserDetailBO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class UserDetailMapper : IOneSideMapper<UserResponseDTO, UserDetailBO> {

    override fun mapInToOut(input: UserResponseDTO): UserDetailBO = with(input) {
        UserDetailBO(
            uuid = uuid,
            username = username,
            email = email,
            firstName = firstName,
            lastName = lastName,
            profilesCount = profilesCount
        )
    }

    override fun mapInListToOutList(input: Iterable<UserResponseDTO>): Iterable<UserDetailBO> =
        input.map(::mapInToOut)
}