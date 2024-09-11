package com.dreamsoftware.saborytv.domain.model

import java.util.Date

data class RecipeBO(
    val id: String,
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val preparationTime: Int,
    val servings: Int,
    val type: RecipeTypeEnum,
    val difficulty: DifficultyEnum,
    val videoUrl: String,
    val imageUrl: String,
    val releasedDate: Date,
    val language: LanguageEnum,
    val isPremium: Boolean
)
