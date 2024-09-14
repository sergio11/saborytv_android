package com.dreamsoftware.saborytv.data.remote.dto.response

import com.google.firebase.Timestamp

data class RecipeDTO(
    val id: String,
    val category: String,
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val preparationTime: Long,
    val difficulty: String,
    val language: String,
    val chefProfileId: String,
    val servings: Long,
    val type: String,
    val videoUrl: String,
    val imageUrl: String,
    val isPremium: Boolean,
    val country: String,
    val releasedData: Timestamp
)
