package com.dreamsoftware.saborytv.data.remote.dto.request

data class AddFavoriteRecipeDTO(
    val profileId: String,
    val id: String,
    val type: String
)
