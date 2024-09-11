package com.dreamsoftware.saborytv.data.remote.mapper

import com.dreamsoftware.saborytv.data.remote.dto.response.RecipeDTO
import com.dreamsoftware.saborytv.utils.IOneSideMapper

internal class RecipeRemoteMapper: IOneSideMapper<Map<String, Any?>, RecipeDTO> {

    private companion object {
        const val UID_KEY = "uid"
        const val TITLE_KEY = "title"
        const val DESCRIPTION_KEY = "description"
        const val IMAGE_URL_KEY = "imageUrl"
        const val VIDEO_URL_KEY = "videoUrl"
        const val TYPE_KEY = "type"
        const val SERVINGS_KEY = "servings"
        const val PREPARATION_TIME_KEY = "preparationTime"
        const val INSTRUCTIONS_KEY = "instructions"
        const val INGREDIENTS_KEY = "ingredients"
        const val CATEGORY_KEY = "category"
        const val IS_PREMIUM_KEY = "isPremium"
    }

    override fun mapInToOut(input: Map<String, Any?>): RecipeDTO = with(input) {
        RecipeDTO(
            id = get(UID_KEY) as String,
            title = get(TITLE_KEY) as String,
            description = get(DESCRIPTION_KEY) as String,
            category = get(CATEGORY_KEY) as String,
            preparationTime = get(PREPARATION_TIME_KEY) as Int,
            servings = get(SERVINGS_KEY) as Int,
            type = get(TYPE_KEY) as String,
            videoUrl = get(VIDEO_URL_KEY) as String,
            ingredients = get(INGREDIENTS_KEY) as List<String>,
            instructions = get(INSTRUCTIONS_KEY) as List<String>,
            isPremium = get(IS_PREMIUM_KEY) as Boolean,
            imageUrl = get(IMAGE_URL_KEY) as String
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<RecipeDTO> =
        input.map(::mapInToOut)
}