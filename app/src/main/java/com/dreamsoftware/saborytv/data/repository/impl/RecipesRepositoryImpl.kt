package com.dreamsoftware.saborytv.data.repository.impl

import com.dreamsoftware.saborytv.data.remote.datasource.IChefProfilesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.IRecipesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.dto.request.AddFavoriteRecipeDTO
import com.dreamsoftware.saborytv.data.remote.dto.request.RecipeFilterDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.ChefProfileDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.RecipeDTO
import com.dreamsoftware.saborytv.data.remote.exception.AddToRecipesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchFeaturedRecipesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecipeByCategoryRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecipeByIdRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecipesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecommendedRecipesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.GetFavoritesByUserRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.HasRecipeInFavoritesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.RemoveFromFavoritesRemoteException
import com.dreamsoftware.saborytv.data.repository.impl.core.SupportRepositoryImpl
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
import com.dreamsoftware.saborytv.domain.repository.IRecipesRepository
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import com.dreamsoftware.saborytv.utils.parallelMap
import kotlinx.coroutines.CoroutineDispatcher

internal class RecipesRepositoryImpl(
    private val recipesRemoteDataSource: IRecipesRemoteDataSource,
    private val favoritesRemoteDataSource: IFavoritesRemoteDataSource,
    private val chefProfileRemoteDataSource: IChefProfilesRemoteDataSource,
    private val filterDataMapper: IOneSideMapper<RecipeFilterDataBO, RecipeFilterDTO>,
    private val recipeMapper: IOneSideMapper<Pair<ChefProfileDTO, RecipeDTO>, RecipeBO>,
    private val addFavoriteMapper: IOneSideMapper<AddFavoriteRecipeBO, AddFavoriteRecipeDTO>,
    dispatcher: CoroutineDispatcher
): SupportRepositoryImpl(dispatcher), IRecipesRepository {

    @Throws(FetchRecipesException::class)
    override suspend fun getRecipes(
        filter: RecipeFilterDataBO,
        includePremium: Boolean
    ): Iterable<RecipeBO> = safeExecute {
        val filterDTO = filterDataMapper.mapInToOut(filter)
        try {
            recipesRemoteDataSource.getRecipes(filterDTO, includePremium).mapRecipes()
        } catch (ex: FetchRecipesRemoteException) {
            throw FetchRecipesException("An error occurred when fetching recipes", ex)
        }
    }

    @Throws(FetchRecipeByIdException::class)
    override suspend fun getRecipeById(id: String): RecipeBO = safeExecute {
        try {
            recipesRemoteDataSource.getRecipeById(id)
                .let { recipe -> chefProfileRemoteDataSource.getChefProfileById(recipe.chefProfileId) to recipe }
                .let(recipeMapper::mapInToOut)
        } catch (ex: FetchRecipeByIdRemoteException) {
            throw FetchRecipeByIdException("An error occurred when fetching the recipe", ex)
        }
    }

    @Throws(FetchRecipesRecommendedException::class)
    override suspend fun getRecipesRecommended(includePremium: Boolean): Iterable<RecipeBO> = safeExecute {
        try {
            recipesRemoteDataSource.getRecommendedRecipes(includePremium).mapRecipes()
        } catch (ex: FetchRecommendedRecipesRemoteException) {
            throw FetchRecipesRecommendedException(
                "An error occurred when fetching the recommended recipes",
                ex
            )
        }
    }

    @Throws(FetchFeaturedRecipesException::class)
    override suspend fun getFeaturedRecipes(includePremium: Boolean): Iterable<RecipeBO> = safeExecute {
        try {
            recipesRemoteDataSource.getFeaturedRecipes(includePremium).mapRecipes()
        } catch (ex: FetchFeaturedRecipesRemoteException) {
            throw FetchFeaturedRecipesException(
                "An error occurred when fetching the featured recipes",
                ex
            )
        }
    }

    @Throws(FetchRecipeByCategoryException::class)
    override suspend fun getRecipesByCategory(
        id: String,
        includePremium: Boolean
    ): Iterable<RecipeBO> = safeExecute {
        try {
            recipesRemoteDataSource.getRecipeByCategory(id, includePremium).mapRecipes()
        } catch (ex: FetchRecipeByCategoryRemoteException) {
            throw FetchRecipeByCategoryException(
                "An error occurred when fetching the recipes",
                ex
            )
        }
    }

    @Throws(AddFavoriteRecipeException::class)
    override suspend fun addFavoriteRecipe(data: AddFavoriteRecipeBO): Boolean = safeExecute {
        try {
            favoritesRemoteDataSource.addFavorite(addFavoriteMapper.mapInToOut(data))
        } catch (ex: AddToRecipesRemoteException) {
            throw AddFavoriteRecipeException(
                "An error occurred when adding recipe to favorites",
                ex
            )
        }
    }

    @Throws(FetchFavoritesRecipesByUserException::class)
    override suspend fun getFavoritesRecipesByProfile(profileId: String): List<RecipeBO> = safeExecute {
        try {
            favoritesRemoteDataSource.getFavoritesByUser(profileId).parallelMap { getRecipeById(id = it.id) }
        } catch (ex: GetFavoritesByUserRemoteException) {
            throw FetchFavoritesRecipesByUserException(
                "An error occurred when fetching favorites",
                ex
            )
        }
    }

    @Throws(VerifyFavoriteRecipeException::class)
    override suspend fun hasRecipeInFavorites(profileId: String, recipeId: String): Boolean = safeExecute {
        try {
            favoritesRemoteDataSource.hasRecipeInFavorites(
                profileId = profileId,
                recipeId = recipeId
            )
        } catch (ex: HasRecipeInFavoritesRemoteException) {
            throw VerifyFavoriteRecipeException(
                "An error occurred when checking favorites",
                ex
            )
        }
    }

    @Throws(RemoveFavoriteRecipeException::class)
    override suspend fun removeFavoriteRecipe(profileId: String, recipeId: String): Boolean = safeExecute {
        try {
            favoritesRemoteDataSource.removeFavorite(
                profileId = profileId,
                recipeId = recipeId
            )
        } catch (ex: RemoveFromFavoritesRemoteException) {
            throw RemoveFavoriteRecipeException(
                "An error occurred when removing recipe from favorites",
                ex
            )
        }
    }

    private suspend fun List<RecipeDTO>.mapRecipes(): Iterable<RecipeBO> =
        parallelMap { recipe -> chefProfileRemoteDataSource.getChefProfileById(recipe.chefProfileId) to recipe }
            .let(recipeMapper::mapInListToOutList)
}