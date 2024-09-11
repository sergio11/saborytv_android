package com.dreamsoftware.saborytv.domain.repository

import com.dreamsoftware.saborytv.domain.exception.FetchChefProfileByIdException
import com.dreamsoftware.saborytv.domain.exception.FetchChefProfilesException
import com.dreamsoftware.saborytv.domain.model.ChefProfileBO

interface IChefProfilesRepository {

    @Throws(FetchChefProfilesException::class)
    suspend fun getChefProfiles(): List<ChefProfileBO>

    @Throws(FetchChefProfileByIdException::class)
    suspend fun getChefProfileById(id: String): ChefProfileBO
}