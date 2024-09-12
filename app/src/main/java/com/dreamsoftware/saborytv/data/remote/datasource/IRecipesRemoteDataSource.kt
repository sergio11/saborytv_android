package com.dreamsoftware.saborytv.data.remote.datasource

import com.dreamsoftware.saborytv.data.remote.dto.request.RecipeFilterDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.RecipeDTO
import com.dreamsoftware.saborytv.data.remote.exception.FetchFeaturedRecipesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecipeByCategoryRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecipeByIdRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecipesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecommendedRecipesRemoteException

interface IRecipesRemoteDataSource {

    @Throws(FetchRecipesRemoteException::class)
    suspend fun getRecipes(filter: RecipeFilterDTO, includePremium: Boolean = false): List<RecipeDTO>

    @Throws(FetchRecipeByIdRemoteException::class)
    suspend fun getRecipeById(id: String): RecipeDTO

    @Throws(FetchRecipeByIdRemoteException::class)
    suspend fun getRecipeByIdList(idList: List<String>, includePremium: Boolean = false): List<RecipeDTO>

    @Throws(FetchRecipeByCategoryRemoteException::class)
    suspend fun getRecipeByCategory(id: String, includePremium: Boolean = false): List<RecipeDTO>

    @Throws(FetchFeaturedRecipesRemoteException::class)
    suspend fun getFeaturedRecipes(includePremium: Boolean = false): List<RecipeDTO>

    @Throws(FetchRecommendedRecipesRemoteException::class)
    suspend fun getRecommendedRecipes(includePremium: Boolean = false): List<RecipeDTO>
}