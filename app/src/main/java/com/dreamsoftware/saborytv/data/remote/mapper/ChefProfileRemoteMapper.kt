package com.dreamsoftware.saborytv.data.remote.mapper

import com.dreamsoftware.saborytv.data.remote.dto.response.ChefProfileDTO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class ChefProfileRemoteMapper: IOneSideMapper<Map<String, Any?>, ChefProfileDTO> {

    private companion object {
        const val UID_KEY = "uid"
        const val NAME_KEY = "name"
        const val DESCRIPTION_KEY = "description"
        const val IMAGE_URL_KEY = "imageUrl"
    }

    override fun mapInToOut(input: Map<String, Any?>): ChefProfileDTO = with(input) {
        ChefProfileDTO(
            id = get(UID_KEY) as String,
            name = get(NAME_KEY) as String,
            description = get(DESCRIPTION_KEY) as String,
            imageUrl = get(IMAGE_URL_KEY) as String,
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<ChefProfileDTO> =
        input.map(::mapInToOut)
}