package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.request.AddFavoriteTrainingDTO
import com.dreamsoftware.saborytv.domain.model.AddFavoriteTrainingBO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class AddFavoriteTrainingMapper :
    IOneSideMapper<AddFavoriteTrainingBO, AddFavoriteTrainingDTO> {

    override fun mapInToOut(input: AddFavoriteTrainingBO): AddFavoriteTrainingDTO = with(input) {
        AddFavoriteTrainingDTO(
            profileId = profileId,
            trainingId = trainingId,
            trainingType = trainingType.name
        )
    }

    override fun mapInListToOutList(input: Iterable<AddFavoriteTrainingBO>): Iterable<AddFavoriteTrainingDTO> =
        input.map(::mapInToOut)
}