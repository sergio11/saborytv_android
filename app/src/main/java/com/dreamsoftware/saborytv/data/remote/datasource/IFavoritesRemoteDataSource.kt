package com.dreamsoftware.saborytv.data.remote.datasource

import com.dreamsoftware.saborytv.data.remote.dto.request.AddFavoriteTrainingDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.FavoriteTrainingDTO
import com.dreamsoftware.saborytv.data.remote.exception.AddToFavoritesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.GetFavoritesByUserRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.HasTrainingInFavoritesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.RemoveAllFavoritesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.RemoveFromFavoritesRemoteException

interface IFavoritesRemoteDataSource {

    @Throws(AddToFavoritesRemoteException::class)
    suspend fun addFavorite(data: AddFavoriteTrainingDTO): Boolean

    @Throws(GetFavoritesByUserRemoteException::class)
    suspend fun getFavoritesByUser(profileId: String): List<FavoriteTrainingDTO>

    @Throws(HasTrainingInFavoritesRemoteException::class)
    suspend fun hasTrainingInFavorites(profileId: String, trainingId: String): Boolean

    @Throws(RemoveFromFavoritesRemoteException::class)
    suspend fun removeFavorite(profileId: String, trainingId: String): Boolean

    @Throws(RemoveAllFavoritesRemoteException::class)
    suspend fun removeFavorites(profileId: String): Boolean
}