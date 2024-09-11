package com.dreamsoftware.saborytv.data.remote.datasource.impl

import com.dreamsoftware.saborytv.data.remote.datasource.IChefProfilesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.dto.response.ChefProfileDTO
import com.dreamsoftware.saborytv.data.remote.exception.FetchChefProfileByIdRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchChefProfilesRemoteException
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class ChefProfilesRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val chefProfilesMapper: IOneSideMapper<Map<String, Any?>, ChefProfileDTO>,
    dispatcher: CoroutineDispatcher
) : SupportFireStoreDataSourceImpl(dispatcher), IChefProfilesRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "saborytv_chef_profiles"
    }

    @Throws(FetchChefProfilesRemoteException::class)
    override suspend fun getChefProfiles(): List<ChefProfileDTO> = try {
        fetchListFromFireStore(
            query = { firebaseStore.collection(COLLECTION_NAME).get() },
            mapper = { chefProfilesMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchChefProfilesRemoteException(
            "An error occurred when trying to fetch chef profiles",
            ex
        )
    }

    @Throws(FetchChefProfileByIdRemoteException::class)
    override suspend fun getChefProfileById(id: String): ChefProfileDTO = try {
        fetchSingleFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .document(id)
                    .get()
            },
            mapper = { chefProfilesMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchChefProfileByIdRemoteException(
            "An error occurred when trying to chef profiles",
            ex
        )
    }
}