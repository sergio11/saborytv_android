package com.dreamsoftware.saborytv.data.remote.dto.request

data class AddFavoriteTrainingDTO(
    val profileId: String,
    val trainingId: String,
    val trainingType: String
)
