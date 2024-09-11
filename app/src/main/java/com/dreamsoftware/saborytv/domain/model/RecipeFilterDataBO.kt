package com.dreamsoftware.saborytv.domain.model

data class RecipeFilterDataBO(
    val type: RecipeTypeEnum,
    val classLanguage: ClassLanguageEnum,
    val difficulty: DifficultyEnum,
    val videoLength: VideoLengthEnum,
    val sortType: SortTypeEnum,
    val chefProfile: String
)
