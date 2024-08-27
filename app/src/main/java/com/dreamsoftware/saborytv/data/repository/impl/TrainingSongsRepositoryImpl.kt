package com.dreamsoftware.saborytv.data.repository.impl

import com.dreamsoftware.saborytv.data.remote.datasource.ITrainingSongsRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.dto.response.TrainingSongDTO
import com.dreamsoftware.saborytv.data.remote.exception.FetchCategoryByIdRemoteException
import com.dreamsoftware.saborytv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.saborytv.domain.exception.FetchCategoryByIdException
import com.dreamsoftware.saborytv.domain.exception.FetchTrainingSongsByIdException
import com.dreamsoftware.saborytv.domain.model.TrainingSongBO
import com.dreamsoftware.saborytv.domain.repository.ITrainingSongsRepository
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class TrainingSongsRepositoryImpl(
    private val trainingSongsRemoteDataSource: ITrainingSongsRemoteDataSource,
    private val trainingSongsMapper: IOneSideMapper<TrainingSongDTO, TrainingSongBO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), ITrainingSongsRepository {

    @Throws(FetchTrainingSongsByIdException::class)
    override suspend fun getSongById(id: String): TrainingSongBO = safeExecute {
        try {
            trainingSongsRemoteDataSource
                .getSongById(id)
                .let(trainingSongsMapper::mapInToOut)
        } catch (ex: FetchCategoryByIdRemoteException) {
            throw FetchCategoryByIdException("An error occurred when fetching training song by $id", ex)
        }
    }
}