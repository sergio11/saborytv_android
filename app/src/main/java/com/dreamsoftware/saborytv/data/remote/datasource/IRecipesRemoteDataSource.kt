package com.dreamsoftware.saborytv.data.remote.datasource

import com.dreamsoftware.saborytv.data.remote.dto.response.RecipeDTO
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecipeByIdRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecipesRemoteException

interface IRecipesRemoteDataSource {

    @Throws(FetchRecipesRemoteException::class)
    suspend fun getRecipes(): List<RecipeDTO>

    @Throws(FetchRecipeByIdRemoteException::class)
    suspend fun getRecipeById(id: String): RecipeDTO
}