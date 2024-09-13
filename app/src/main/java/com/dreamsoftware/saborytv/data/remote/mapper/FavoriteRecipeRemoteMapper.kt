package com.dreamsoftware.saborytv.data.remote.mapper

import com.dreamsoftware.saborytv.data.remote.dto.response.FavoriteRecipeDTO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class FavoriteRecipeRemoteMapper: IOneSideMapper<Map<String, Any?>, FavoriteRecipeDTO> {

    private companion object {
        const val ID_KEY = "id"
    }

    override fun mapInToOut(input: Map<String, Any?>): FavoriteRecipeDTO = with(input) {
        FavoriteRecipeDTO(
            id = get(ID_KEY) as String
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<FavoriteRecipeDTO> =
        input.map(::mapInToOut)
}