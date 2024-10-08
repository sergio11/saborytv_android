package com.dreamsoftware.saborytv.data.remote.mapper

import com.dreamsoftware.saborytv.data.remote.dto.response.RecipeDTO
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import com.google.firebase.Timestamp

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
        const val LANGUAGE_KEY = "language"
        const val DIFFICULTY_KEY = "difficulty"
        const val CHEF_PROFILE_ID_KEY = "chef"
        const val COUNTRY_KEY = "country"
        const val RELEASED_DATE_KEY = "releasedDate"
    }

    override fun mapInToOut(input: Map<String, Any?>): RecipeDTO = with(input) {
        RecipeDTO(
            id = get(UID_KEY) as String,
            title = get(TITLE_KEY) as String,
            description = get(DESCRIPTION_KEY) as String,
            category = get(CATEGORY_KEY) as String,
            preparationTime = get(PREPARATION_TIME_KEY) as Long,
            servings = get(SERVINGS_KEY) as Long,
            type = get(TYPE_KEY) as String,
            videoUrl = get(VIDEO_URL_KEY) as String,
            ingredients = get(INGREDIENTS_KEY) as List<String>,
            instructions = get(INSTRUCTIONS_KEY) as List<String>,
            isPremium = get(IS_PREMIUM_KEY) as Boolean,
            imageUrl = get(IMAGE_URL_KEY) as String,
            language = get(LANGUAGE_KEY) as String,
            difficulty = get(DIFFICULTY_KEY) as String,
            chefProfileId = get(CHEF_PROFILE_ID_KEY) as String,
            country = get(COUNTRY_KEY) as String,
            releasedData = get(RELEASED_DATE_KEY) as Timestamp
        )
    }

    override fun mapInListToOutList(input: Iterable<Map<String, Any?>>): Iterable<RecipeDTO> =
        input.map(::mapInToOut)
}