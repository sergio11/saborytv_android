package com.dreamsoftware.saborytv.domain.model

data class AddFavoriteRecipeBO(
    val profileId: String,
    val id: String,
    val type: TrainingTypeEnum
)
