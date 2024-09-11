package com.dreamsoftware.saborytv.data.remote.datasource.impl

import com.dreamsoftware.saborytv.data.remote.datasource.IRecipesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.dto.response.RecipeDTO
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecipeByIdRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecipesRemoteException
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class RecipesRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val recipesMapper: IOneSideMapper<Map<String, Any?>, RecipeDTO>,
    dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), IRecipesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "saborytv_recipes"
    }

    @Throws(FetchRecipesRemoteException::class)
    override suspend fun getRecipes(): List<RecipeDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { recipesMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRecipesRemoteException("An error occurred when trying to fetch recipes", ex)
    }

    @Throws(FetchRecipeByIdRemoteException::class)
    override suspend fun getRecipeById(id: String): RecipeDTO = try {
        fetchSingleFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME)
                .document(id)
                .get() },
            mapper = { recipesMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchRecipeByIdRemoteException("An error occurred when trying to fetch the recipes identified by $id", ex)
    }
}