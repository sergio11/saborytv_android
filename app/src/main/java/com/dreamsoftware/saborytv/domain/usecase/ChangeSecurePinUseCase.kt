package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.exception.InvalidDataException
import com.dreamsoftware.saborytv.domain.model.ProfileBO
import com.dreamsoftware.saborytv.domain.model.UpdatedProfileRequestBO
import com.dreamsoftware.saborytv.domain.repository.IProfilesRepository
import com.dreamsoftware.saborytv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class ChangeSecurePinUseCase(
    private val profilesRepository: IProfilesRepository,
    private val validator: IBusinessEntityValidator<UpdatedProfileRequestBO>
): FudgeTvUseCaseWithParams<ChangeSecurePinUseCase.Params, ProfileBO>() {

    override suspend fun onExecuted(params: Params): ProfileBO = with(params) {
        with(profilesRepository) {
            verifyPin(profileId, currentSecurePin)
            toUpdatedProfileRequestBO().let { updatedProfileRequestBO ->
                validator.validate(updatedProfileRequestBO).takeIf { it.isNotEmpty() }?.let { errors ->
                    throw InvalidDataException(errors, "Invalid data provided")
                } ?: run {
                    updateProfile(profileId, updatedProfileRequestBO)
                }
            }
        }
    }

    private fun Params.toUpdatedProfileRequestBO() = UpdatedProfileRequestBO(pin = newSecurePin)

    data class Params(
        val profileId: String,
        val currentSecurePin: Int,
        val newSecurePin: Int
    )
}