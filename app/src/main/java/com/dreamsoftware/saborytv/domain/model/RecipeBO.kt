package com.dreamsoftware.saborytv.domain.model

import java.util.Date

data class RecipeBO(
    val id: String,
    val title: String,
    val description: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val chefProfileName: String,
    val chefProfileId: String,
    val preparationTime: Long,
    val servings: Long,
    val type: RecipeTypeEnum,
    val difficulty: DifficultyEnum,
    val videoUrl: String,
    val imageUrl: String,
    val releasedDate: Date,
    val language: LanguageEnum,
    val isPremium: Boolean,
    val country: String
)
