package com.dreamsoftware.saborytv.data.repository.impl

import com.dreamsoftware.saborytv.data.remote.datasource.IChefProfilesRemoteDataSource
import com.dreamsoftware.saborytv.data.remote.dto.response.ChefProfileDTO
import com.dreamsoftware.saborytv.data.remote.exception.FetchChefProfileByIdRemoteException
import com.dreamsoftware.saborytv.data.remote.exception.FetchChefProfilesRemoteException
import com.dreamsoftware.saborytv.data.repository.impl.core.SupportRepositoryImpl
import com.dreamsoftware.saborytv.domain.exception.FetchChefProfileByIdException
import com.dreamsoftware.saborytv.domain.exception.FetchChefProfilesException
import com.dreamsoftware.saborytv.domain.model.ChefProfileBO
import com.dreamsoftware.saborytv.domain.repository.IChefProfilesRepository
import com.dreamsoftware.saborytv.utils.IOneSideMapper
import kotlinx.coroutines.CoroutineDispatcher

internal class ChefProfilesRepositoryImpl(
    private val chefProfilesRemoteDataSource: IChefProfilesRemoteDataSource,
    private val chefProfileMapper: IOneSideMapper<ChefProfileDTO, ChefProfileBO>,
    dispatcher: CoroutineDispatcher
) : SupportRepositoryImpl(dispatcher), IChefProfilesRepository {

    @Throws(FetchChefProfilesException::class)
    override suspend fun getChefProfiles(): List<ChefProfileBO> = safeExecute {
        try {
            chefProfilesRemoteDataSource
                .getChefProfiles()
                .let(chefProfileMapper::mapInListToOutList)
                .toList()
        } catch (ex: FetchChefProfilesRemoteException) {
            throw FetchChefProfilesException("An error occurred when fetching chef profiles", ex)
        }
    }

    @Throws(FetchChefProfileByIdException::class)
    override suspend fun getChefProfileById(id: String): ChefProfileBO = safeExecute {
        try {
            chefProfilesRemoteDataSource
                .getChefProfileById(id)
                .let(chefProfileMapper::mapInToOut)
        } catch (ex: FetchChefProfileByIdRemoteException) {
            throw FetchChefProfileByIdException("An error occurred when fetching the chef profile by id: $id", ex)
        }
    }
}