package com.dreamsoftware.saborytv.data.remote.mapper

import com.dreamsoftware.saborytv.data.preferences.dto.ProfileSelectedPreferenceDTO
import com.dreamsoftware.saborytv.domain.model.AvatarTypeEnum
import com.dreamsoftware.saborytv.domain.model.ProfileBO
import com.dreamsoftware.saborytv.utils.IMapper
import com.dreamsoftware.saborytv.utils.enumNameOfOrDefault

internal class ProfileSessionMapper: IMapper<ProfileBO, ProfileSelectedPreferenceDTO> {

    override fun mapInToOut(input: ProfileBO): ProfileSelectedPreferenceDTO = with(input) {
        ProfileSelectedPreferenceDTO(
            uuid = uuid,
            alias = alias,
            isSecured = isSecured,
            type = avatarType.name
        )
    }

    override fun mapInListToOutList(input: Iterable<ProfileBO>): Iterable<ProfileSelectedPreferenceDTO> =
        input.map(::mapInToOut)

    override fun mapOutToIn(input: ProfileSelectedPreferenceDTO): ProfileBO = with(input) {
        ProfileBO(
            uuid = uuid,
            alias = alias,
            isSecured = isSecured,
            avatarType = enumNameOfOrDefault(type, AvatarTypeEnum.BOY)
        )
    }

    override fun mapOutListToInList(input: Iterable<ProfileSelectedPreferenceDTO>): Iterable<ProfileBO> =
        input.map(::mapOutToIn)
}