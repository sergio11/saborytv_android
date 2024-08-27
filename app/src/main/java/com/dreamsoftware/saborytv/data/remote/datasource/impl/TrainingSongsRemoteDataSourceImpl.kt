package com.dreamsoftware.saborytv.data.remote.datasource.impl

import com.dreamsoftware.saborytv.data.remote.datasource.ITrainingSongsRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.datasource.impl.core.SupportFireStoreDataSourceImpl
import com.dreamsoftware.saborytv.data.remote.dto.response.TrainingSongDTO
import com.dreamsoftware.saborytv.data.remote.exception.FetchCategoriesRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchTrainingSongByIdRemoteException
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineDispatcher

internal class TrainingSongsRemoteDataSourceImpl(
    private val firebaseStore: FirebaseFirestore,
    private val trainingSongsMapper: IOneSideMapper<Map<String, Any?>, TrainingSongDTO>,
    dispatcher: CoroutineDispatcher
) : SupportFireStoreDataSourceImpl(dispatcher), ITrainingSongsRemoteDataSource {

    private companion object {
        const val COLLECTION_NAME = "training_songs"
    }

    @Throws(FetchTrainingSongByIdRemoteException::class)
    override suspend fun getSongById(songId: String): TrainingSongDTO = try {
        fetchSingleFromFireStore(
            query = {
                firebaseStore.collection(COLLECTION_NAME)
                    .document(songId)
                    .get()
            },
            mapper = { trainingSongsMapper.mapInToOut(it) }
        )
    } catch (ex: Exception) {
        throw FetchCategoriesRemoteException(
            "An error occurred when trying to fetch song with id $songId",
            ex
        )
    }
}