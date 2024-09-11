package com.dreamsoftware.saborytv.data.remote.datasource

import com.dreamsoftware.saborytv.data.remote.dto.response.ChefProfileDTO
import com.dreamsoftware.saborytv.data.remote.exception.FetchChefProfileByIdRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchChefProfilesRemoteException

interface IChefProfilesRemoteDataSource {

    @Throws(FetchChefProfilesRemoteException::class)
    suspend fun getChefProfiles(): List<ChefProfileDTO>

    @Throws(FetchChefProfileByIdRemoteException::class)
    suspend fun getChefProfileById(id: String): ChefProfileDTO
}