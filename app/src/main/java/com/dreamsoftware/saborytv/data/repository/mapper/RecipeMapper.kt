package com.dreamsoftware.saborytv.data.repository.mapper

import com.dreamsoftware.saborytv.data.remote.dto.response.ChefProfileDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.RecipeDTO
import com.dreamsoftware.saborytv.domain.model.DifficultyEnum
import com.dreamsoftware.saborytv.domain.model.LanguageEnum
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import com.dreamsoftware.saborytv.domain.model.RecipeTypeEnum
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import com.dreamsoftware.saborytv.utils.enumNameOfOrDefault

internal class RecipeMapper : IOneSideMapper<Pair<ChefProfileDTO, RecipeDTO>, RecipeBO> {

    override fun mapInToOut(input: Pair<ChefProfileDTO, RecipeDTO>): RecipeBO = with(input) {
        RecipeBO(
            id = second.id,
            title = second.title,
            description = second.description,
            ingredients = second.ingredients,
            instructions = second.instructions,
            chefProfileName = first.name,
            chefProfileId = first.id,
            preparationTime = second.preparationTime,
            servings = second.servings,
            type = enumNameOfOrDefault(second.type, RecipeTypeEnum.VEGETARIAN),
            difficulty = enumNameOfOrDefault(second.difficulty, DifficultyEnum.EASY),
            videoUrl = second.videoUrl,
            imageUrl = second.imageUrl,
            releasedDate = second.releasedData.toDate(),
            language = enumNameOfOrDefault(second.language, LanguageEnum.ENGLISH),
            isPremium = second.isPremium,
            country = second.country
        )
    }

    override fun mapInListToOutList(input: Iterable<Pair<ChefProfileDTO, RecipeDTO>>): Iterable<RecipeBO> =
        input.map(::mapInToOut)
}