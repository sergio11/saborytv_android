package com.dreamsoftware.saborytv.data.remote.mapper

import com.dreamsoftware.saborytv.data.remote.dto.request.AddFavoriteRecipeDTO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class AddFavoriteRecipeRemoteMapper:
    IOneSideMapper<AddFavoriteRecipeDTO, Map<String, Any?>> {

    private companion object {
        const val ID_KEY = "id"
        const val TYPE_KEY = "type"
    }

    override fun mapInToOut(input: AddFavoriteRecipeDTO): Map<String, Any?> = with(input) {
        hashMapOf(
            ID_KEY to id,
            TYPE_KEY to type
        )
    }

    override fun mapInListToOutList(input: Iterable<AddFavoriteRecipeDTO>): Iterable<Map<String, Any?>> =
        input.map(::mapInToOut)
}