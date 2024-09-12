package com.dreamsoftware.saborytv.data.remote.dto.request

data class RecipeFilterDTO(
    val type: String?,
    val language: String?,
    val difficulty: String?,
    val videoLength: IntRange?,
    val chefProfile: String?,
    val orderByReleasedDateDesc: Boolean = true,
    val priorityFeatured: Boolean = false
)
