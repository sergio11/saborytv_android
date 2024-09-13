package com.dreamsoftware.saborytv.data.remote.datasource.impl

import com.dreamsoftware.saborytv.data.remote.datasource.IRecipesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.dto.request.RecipeFilterDTO
import com.dreamsoftware.saborytv.data.remote.dto.response.RecipeDTO
import com.dreamsoftware.saborytv.data.remote.exception.FetchFeaturedRecipesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecipeByCategoryRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecipeByIdRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecipesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchRecommendedRecipesRemoteException
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineDispatcher

internal class RecipesRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val recipesMapper: IOneSideMapper<Map<String, Any?>, RecipeDTO>,
    dispatcher: CoroutineDispatcher
): SupportFireStoreDataSourceImpl(dispatcher), IRecipesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "saborytv_recipes"
        const val IS_PREMIUM = "isPremium"
        const val ID_FIELD = "uid"
        const val CATEGORY_FIELD = "category"
        const val IS_RECOMMENDED_FIELD = "isRecommended"
        const val IS_FEATURED_FIELD = "isFeatured"
        const val LANGUAGE = "language"
        const val DURATION = "duration"
        const val DIFFICULTY = "difficulty"
        const val RELEASED_DATE = "releasedDate"
        const val TYPE = "type"
        const val CHEF_PROFILE = "chef"
    }

    @Throws(FetchRecipesRemoteException::class)
    override suspend fun getRecipes(
        filter: RecipeFilterDTO,
        includePremium: Boolean
    ): List<RecipeDTO> = try {
        fetchListFromFireStore(
            query = {
                with(filter) {
                    var query: Query = firebaseStore.collection(COLLECTION_NAME)

                    language?.let { query = query.whereEqualTo(LANGUAGE, it) }
                    difficulty?.let { query = query.whereEqualTo(DIFFICULTY, it) }
                    type?.let { query = query.whereEqualTo(TYPE, it) }
                    chefProfile?.let { query = query.whereEqualTo(CHEF_PROFILE, it) }
                    videoLength?.let {
                        query = query.whereGreaterThanOrEqualTo(DURATION, it.first.toString())
                            .whereLessThanOrEqualTo(DURATION, it.last.toString())
                    }
                    // Apply sorting
                    if (priorityFeatured) {
                        query = query.orderBy(IS_FEATURED_FIELD, Query.Direction.DESCENDING)
                    }
                    query = if (orderByReleasedDateDesc) {
                        query.orderBy(RELEASED_DATE, Query.Direction.DESCENDING)
                    } else {
                        query.orderBy(RELEASED_DATE, Query.Direction.ASCENDING)
                    }
                    if(!includePremium) {
                        query.whereEqualTo(IS_PREMIUM, false)
                    }
                    query.get()
                }
            },
            mapper = { data -> recipesMapper.mapInToOut(data) }
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

    @Throws(FetchRecipeByIdRemoteException::class)
    override suspend fun getRecipeByIdList(
        idList: List<String>,
        includePremium: Boolean
    ): List<RecipeDTO> = try {
        fetchListFromFireStore(
            query = {
                val query = firebaseStore
                    .collection(COLLECTION_NAME)
                    .whereIn(ID_FIELD, idList)
                if(!includePremium) {
                    query.whereEqualTo(IS_PREMIUM, false)
                }
                query.get()
            },
            mapper = { data -> recipesMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRecipeByIdRemoteException(
            "An error occurred when trying to fetch recipes by ID list",
            ex
        )
    }

    @Throws(FetchRecipeByCategoryRemoteException::class)
    override suspend fun getRecipeByCategory(id: String, includePremium: Boolean): List<RecipeDTO> = try {
        fetchListFromFireStore(
            query = {
                val query = firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(CATEGORY_FIELD, id)
                if(!includePremium) {
                    query.whereEqualTo(IS_PREMIUM, false)
                }
                query.get()
            },
            mapper = { data -> recipesMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRecipeByCategoryRemoteException(
            "An error occurred when trying to fetch recipes by category",
            ex
        )
    }

    @Throws(FetchFeaturedRecipesRemoteException::class)
    override suspend fun getFeaturedRecipes(includePremium: Boolean): List<RecipeDTO> = try {
        fetchListFromFireStore(
            query = {
                val query = firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(IS_FEATURED_FIELD, true)
                if(!includePremium) {
                    query.whereEqualTo(IS_PREMIUM, false)
                }
                query.get()
            },
            mapper = { data -> recipesMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchFeaturedRecipesRemoteException(
            "An error occurred when trying to fetch featured recipes",
            ex
        )
    }

    @Throws(FetchRecommendedRecipesRemoteException::class)
    override suspend fun getRecommendedRecipes(includePremium: Boolean): List<RecipeDTO> = try {
        fetchListFromFireStore(
            query = {
                val query = firebaseStore.collection(COLLECTION_NAME)
                    .whereEqualTo(IS_RECOMMENDED_FIELD, true)
                if(!includePremium) {
                    query.whereEqualTo(IS_PREMIUM, false)
                }
                query.get()
            },
            mapper = { data -> recipesMapper.mapInToOut(data) }
        )
    } catch (ex: Exception) {
        throw FetchRecommendedRecipesRemoteException(
            "An error occurred when trying to fetch recommended recipes",
            ex
        )
    }
}