package com.dreamsoftware.saborytv.data.remote.mapper

import com.dreamsoftware.saborytv.data.remote.dto.request.CreateProfileRequestDTO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class CreateProfileRequestRemoteMapper:
    IOneSideMapper<CreateProfileRequestDTO, Map<String, Any?>> {

    private companion object {
        const val UUID_KEY = "uuid"
        const val ALIAS_KEY = "alias"
        const val IS_SECURED_KEY = "isSecured"
        const val PIN_KEY = "pin"
        const val AVATAR_TYPE_KEY = "avatarType"
        const val USER_ID = "userId"
    }

    override fun mapInToOut(input: CreateProfileRequestDTO): Map<String, Any?> = with(input) {
        mapOf(
            UUID_KEY to uid,
            ALIAS_KEY to alias,
            PIN_KEY to pin,
            AVATAR_TYPE_KEY to avatarType,
            USER_ID to userId,
            IS_SECURED_KEY to true
        )
    }

    override fun mapInListToOutList(input: Iterable<CreateProfileRequestDTO>): Iterable<Map<String, Any?>> =
        input.map(::mapInToOut)
}