package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.request.RecipesFilterDTO
import com.dreamsoftware.saborytv.domain.model.LanguageEnum
import com.dreamsoftware.saborytv.domain.model.DifficultyEnum
import com.dreamsoftware.saborytv.domain.model.SortTypeEnum
import com.dreamsoftware.saborytv.domain.model.RecipeFilterDataBO
import com.dreamsoftware.saborytv.domain.model.VideoLengthEnum
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class RecipeFilterDataMapper: IOneSideMapper<RecipeFilterDataBO, RecipesFilterDTO> {
    override fun mapInToOut(input: RecipeFilterDataBO): RecipesFilterDTO = with(input) {
        RecipesFilterDTO(
            language = language.takeIf { it != LanguageEnum.NOT_SET }?.value,
            type = type.value,
            difficulty = difficulty.takeIf { it != DifficultyEnum.NOT_SET }?.value,
            videoLength = videoLength.takeIf { it != VideoLengthEnum.NOT_SET }?.range,
            orderByReleasedDateDesc = sortType == SortTypeEnum.NEWEST || sortType == SortTypeEnum.NOT_SET,
            chefProfile = chefProfile.takeIf { it.isNotBlank() },
            priorityFeatured = sortType == SortTypeEnum.RELEVANCE
        )
    }

    override fun mapInListToOutList(input: Iterable<RecipeFilterDataBO>): Iterable<RecipesFilterDTO> =
        input.map(::mapInToOut)
}