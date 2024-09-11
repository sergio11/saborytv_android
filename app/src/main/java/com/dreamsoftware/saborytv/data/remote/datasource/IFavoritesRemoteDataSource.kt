package com.dreamsoftware.saborytv.data.remote.datasource

import com.dreamsoftware.saborytv.data.remote.dto.request.AddFavoriteRecipeDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.FavoriteRecipeDTO
import com.dreamsoftware.saborytv.data.remote.exception.AddToRecipesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.GetFavoritesByUserRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.HasRecipeInFavoritesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.RemoveAllFavoritesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.RemoveFromFavoritesRemoteException

interface IFavoritesRemoteDataSource {

    @Throws(AddToRecipesRemoteException::class)
    suspend fun addFavorite(data: AddFavoriteRecipeDTO): Boolean

    @Throws(GetFavoritesByUserRemoteException::class)
    suspend fun getFavoritesByUser(profileId: String): List<FavoriteRecipeDTO>

    @Throws(HasRecipeInFavoritesRemoteException::class)
    suspend fun hasRecipeInFavorites(profileId: String, recipeId: String): Boolean

    @Throws(RemoveFromFavoritesRemoteException::class)
    suspend fun removeFavorite(profileId: String, recipeId: String): Boolean

    @Throws(RemoveAllFavoritesRemoteException::class)
    suspend fun removeFavorites(profileId: String): Boolean
}