package com.dreamsoftware.saborytv.domain.model

data class RecipeFilterDataBO(
    val type: RecipeTypeEnum,
    val language: LanguageEnum,
    val difficulty: DifficultyEnum,
    val videoLength: VideoLengthEnum,
    val sortType: SortTypeEnum,
    val chefProfile: String
)
