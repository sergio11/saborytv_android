package com.dreamsoftware.saborytv.data.remote.dto.response

data class RecipeDTO(
    val id: String,
    val category: String,
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val preparationTime: Int,
    val difficulty: String,
    val language: String,
    val chefProfileId: String,
    val servings: Int,
    val type: String,
    val videoUrl: String,
    val imageUrl: String,
    val isPremium: Boolean
)
