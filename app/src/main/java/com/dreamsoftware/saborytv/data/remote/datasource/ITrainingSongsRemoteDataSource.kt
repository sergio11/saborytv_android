package com.dreamsoftware.saborytv.data.remote.datasource

import com.dreamsoftware.saborytv.data.remote.dto.response.TrainingSongDTO
import com.dreamsoftware.saborytv.data.remote.exception.FetchTrainingSongByIdRemoteException

interface ITrainingSongsRemoteDataSource {

    @Throws(FetchTrainingSongByIdRemoteException::class)
    suspend fun getSongById(songId: String): TrainingSongDTO
}