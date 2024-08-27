package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.AppLanguageEnum
import com.dreamsoftware.saborytv.domain.model.UnitsEnum
import com.dreamsoftware.saborytv.domain.model.UserPreferenceBO
import com.dreamsoftware.saborytv.domain.model.VideoQualityEnum
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.saborytv.utils.enumOfOrDefault
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class SaveUserPreferencesUseCase(
    private val userRepository: IUserRepository
) : FudgeTvUseCaseWithParams<SaveUserPreferencesUseCase.Params, Unit>() {

    override suspend fun onExecuted(params: Params): Unit = with(params) {
        userRepository.saveUserPreferences(toUserPreferencesBO())
    }

    private fun Params.toUserPreferencesBO() = UserPreferenceBO(
        units = enumOfOrDefault({it.value == units}, UnitsEnum.METRIC),
        language = enumOfOrDefault({it.value == language}, AppLanguageEnum.ENGLISH),
        videoQuality = enumOfOrDefault({it.value == videoQuality}, VideoQualityEnum.FULL_HD)
    )

    data class Params(
        val units: String,
        val language: String,
        val videoQuality: String
    )
}