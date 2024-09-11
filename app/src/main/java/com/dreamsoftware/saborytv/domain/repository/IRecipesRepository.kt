package com.dreamsoftware.saborytv.domain.repository

import com.dreamsoftware.saborytv.domain.exception.AddFavoriteRecipeException
import com.dreamsoftware.saborytv.domain.exception.FetchFavoritesRecipesByUserException
import com.dreamsoftware.saborytv.domain.exception.FetchFeaturedRecipesException
import com.dreamsoftware.saborytv.domain.exception.FetchRecipeByCategoryException
import com.dreamsoftware.saborytv.domain.exception.FetchRecipeByIdException
import com.dreamsoftware.saborytv.domain.exception.FetchRecipesException
import com.dreamsoftware.saborytv.domain.exception.FetchRecipesRecommendedException
import com.dreamsoftware.saborytv.domain.exception.RemoveFavoriteRecipeException
import com.dreamsoftware.saborytv.domain.exception.VerifyFavoriteRecipeException
import com.dreamsoftware.saborytv.domain.model.AddFavoriteRecipeBO
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import com.dreamsoftware.saborytv.domain.model.RecipeFilterDataBO

interface IRecipesRepository {

    @Throws(FetchRecipesException::class)
    suspend fun getRecipes(data: RecipeFilterDataBO, includePremium: Boolean = false): Iterable<RecipeBO>

    @Throws(FetchRecipeByIdException::class)
    suspend fun getRecipeById(id: String): RecipeBO

    @Throws(FetchRecipesRecommendedException::class)
    suspend fun getRecipesRecommended(includePremium: Boolean = false): Iterable<RecipeBO>

    @Throws(FetchFeaturedRecipesException::class)
    suspend fun getFeaturedRecipes(includePremium: Boolean = false): Iterable<RecipeBO>

    @Throws(FetchRecipeByCategoryException::class)
    suspend fun getRecipesByCategory(id: String, includePremium: Boolean = false): Iterable<RecipeBO>

    @Throws(AddFavoriteRecipeException::class)
    suspend fun addFavoriteRecipe(data: AddFavoriteRecipeBO): Boolean

    @Throws(FetchFavoritesRecipesByUserException::class)
    suspend fun getFavoritesRecipesByProfile(profileId: String): List<RecipeBO>

    @Throws(VerifyFavoriteRecipeException::class)
    suspend fun hasRecipeInFavorites(profileId: String, recipeId: String): Boolean

    @Throws(RemoveFavoriteRecipeException::class)
    suspend fun removeFavoriteRecipe(profileId: String, recipeId: String): Boolean
}