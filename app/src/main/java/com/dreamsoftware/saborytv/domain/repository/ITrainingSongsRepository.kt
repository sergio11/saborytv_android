package com.dreamsoftware.saborytv.domain.repository

import com.dreamsoftware.saborytv.domain.exception.FetchTrainingSongsByIdException
import com.dreamsoftware.saborytv.domain.model.TrainingSongBO

interface ITrainingSongsRepository {

    @Throws(FetchTrainingSongsByIdException::class)
    suspend fun getSongById(id: String): TrainingSongBO
}