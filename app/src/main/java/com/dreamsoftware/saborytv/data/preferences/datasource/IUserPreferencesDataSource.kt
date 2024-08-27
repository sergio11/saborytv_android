package com.dreamsoftware.saborytv.data.preferences.datasource

import com.dreamsoftware.saborytv.data.preferences.dto.UserPreferencesDTO
import com.dreamsoftware.saborytv.data.preferences.exception.FetchUserPreferencesLocalException
import com.dreamsoftware.saborytv.data.preferences.exception.SaveUserPreferencesLocalException

interface IUserPreferencesDataSource {

    @Throws(SaveUserPreferencesLocalException::class)
    suspend fun save(data: UserPreferencesDTO)

    @Throws(FetchUserPreferencesLocalException::class)
    suspend fun get(): UserPreferencesDTO
}