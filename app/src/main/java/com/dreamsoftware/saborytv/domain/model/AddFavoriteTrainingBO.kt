package com.dreamsoftware.saborytv.domain.model

data class AddFavoriteTrainingBO(
    val profileId: String,
    val trainingId: String,
    val trainingType: TrainingTypeEnum
)
