package com.dreamsoftware.saborytv.data.remote.datasource.impl

import com.dreamsoftware.saborytv.data.remote.datasource.IFavoritesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.dto.request.AddFavoriteRecipeDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.FavoriteRecipeDTO
import com.dreamsoftware.saborytv.data.remote.exception.AddToRecipesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.DeleteProfileRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.GetFavoritesByUserRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.HasRecipeInFavoritesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.RemoveAllFavoritesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.RemoveFromFavoritesRemoteException
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

internal class FavoritesRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val addFavoriteMapper: IOneSideMapper<AddFavoriteRecipeDTO, Map<String, Any?>>,
    private val favoriteMapper: IOneSideMapper<Map<String, Any?>, FavoriteRecipeDTO>,
    private val dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), IFavoritesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "saborytv_favorite_recipes"
        const val SUB_COLLECTION_NAME = "recipes"
    }

    @Throws(AddToRecipesRemoteException::class)
    override suspend fun addFavorite(data: AddFavoriteRecipeDTO): Boolean = try {
            withContext(dispatcher) {
                firebaseStore.collection(COLLECTION_NAME)
                    .document(data.profileId)
                    .collection(SUB_COLLECTION_NAME)
                    .document(data.id)
                    .set(addFavoriteMapper.mapInToOut(data), SetOptions.merge())
                    .await()
                true
            }
        } catch (ex: Exception) {
            throw AddToRecipesRemoteException(
                "An error occurred when trying to add recipe to favorites",
                ex
            )
        }

    @Throws(GetFavoritesByUserRemoteException::class)
    override suspend fun getFavoritesByUser(profileId: String): List<FavoriteRecipeDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore
                .collection(COLLECTION_NAME)
                .document(profileId)
                .collection(SUB_COLLECTION_NAME)
                .get() },
            mapper = { favoriteMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw GetFavoritesByUserRemoteException("An error occurred when trying to fetch favorite trainings", ex)
    }

    @Throws(HasRecipeInFavoritesRemoteException::class)
    override suspend fun hasRecipeInFavorites(profileId: String, recipeId: String): Boolean = try {
        withContext(dispatcher) {
            val document = firebaseStore
                .collection(COLLECTION_NAME)
                .document(profileId)
                .collection(SUB_COLLECTION_NAME)
                .document(recipeId)
                .get()
                .await()
            document.exists()
        }
    } catch (ex: Exception) {
        throw HasRecipeInFavoritesRemoteException("An error occurred when trying to check favorites", ex)
    }

    @Throws(RemoveFromFavoritesRemoteException::class)
    override suspend fun removeFavorite(profileId: String, recipeId: String): Boolean = try {
        withContext(dispatcher) {
            firebaseStore
                .collection(COLLECTION_NAME)
                .document(profileId)
                .collection(SUB_COLLECTION_NAME)
                .document(recipeId)
                .delete()
                .await()
            true
        }
    } catch (ex: Exception) {
        throw DeleteProfileRemoteException(
            "An error occurred when trying to remove training $recipeId from favorites",
            ex
        )
    }

    @Throws(RemoveAllFavoritesRemoteException::class)
    override suspend fun removeFavorites(profileId: String): Boolean = try {
        withContext(dispatcher) {
            // Get all documents in the sub-collection (favorites) for the given profile
            val favoriteDocuments = firebaseStore
                .collection(COLLECTION_NAME)
                .document(profileId)
                .collection(SUB_COLLECTION_NAME)
                .get()
                .await()

            if (!favoriteDocuments.isEmpty) {
                // Create a batch to delete all documents at once
                val batch = firebaseStore.batch()
                for (document in favoriteDocuments.documents) {
                    batch.delete(document.reference)
                }
                // Commit the batch
                batch.commit().await()
            }
            true
        }
    } catch (ex: Exception) {
        throw RemoveAllFavoritesRemoteException(
            "An error occurred when trying to remove all favorites for profile $profileId",
            ex
        )
    }

}