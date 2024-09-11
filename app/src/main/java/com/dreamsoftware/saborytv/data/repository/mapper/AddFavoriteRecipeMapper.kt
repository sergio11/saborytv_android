package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.request.AddFavoriteRecipeDTO
import com.dreamsoftware.saborytv.domain.model.AddFavoriteRecipeBO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class AddFavoriteRecipeMapper :
    IOneSideMapper<AddFavoriteRecipeBO, AddFavoriteRecipeDTO> {

    override fun mapInToOut(input: AddFavoriteRecipeBO): AddFavoriteRecipeDTO = with(input) {
        AddFavoriteRecipeDTO(
            profileId = profileId,
            id = id,
            type = type.name
        )
    }

    override fun mapInListToOutList(input: Iterable<AddFavoriteRecipeBO>): Iterable<AddFavoriteRecipeDTO> =
        input.map(::mapInToOut)
}