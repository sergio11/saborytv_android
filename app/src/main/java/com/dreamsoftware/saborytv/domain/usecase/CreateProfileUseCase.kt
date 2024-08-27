package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.exception.InvalidDataException

import com.dreamsoftware.saborytv.domain.exception.UserProfilesLimitReachedException
import com.dreamsoftware.saborytv.domain.model.AvatarTypeEnum
import com.dreamsoftware.saborytv.domain.model.CreateProfileRequestBO
import com.dreamsoftware.saborytv.domain.repository.IProfilesRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.saborytv.domain.validation.IBusinessEntityValidator
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams
import java.util.UUID

class CreateProfileUseCase(
    private val userRepository: IUserRepository,
    private val profilesRepository: IProfilesRepository,
    private val validator: IBusinessEntityValidator<CreateProfileRequestBO>
): FudgeTvUseCaseWithParams<CreateProfileUseCase.Params, Boolean>() {

    private companion object {
        const val MAX_PROFILES_BY_USER = 4
    }

    override suspend fun onExecuted(params: Params): Boolean = with(params) {
        val userUid = userRepository.getAuthenticatedUid()
        toCreateProfileRequestBO(userUid).let { createProfileRequestBO ->
            validator.validate(createProfileRequestBO).takeIf { it.isNotEmpty() }?.let { errors ->
                throw InvalidDataException(errors, "Invalid data provided")
            } ?: run {
                with(profilesRepository) {
                    if(countProfilesByUser(userUid) < MAX_PROFILES_BY_USER) {
                        createProfile(createProfileRequestBO)
                    } else {
                        throw UserProfilesLimitReachedException(
                            maxProfilesLimit = MAX_PROFILES_BY_USER,
                            message = "User with id $userUid has reached profile creation limit"
                        )
                    }
                }
            }
        }
    }

    private fun Params.toCreateProfileRequestBO(userId: String) = CreateProfileRequestBO(
        uid = UUID.randomUUID().toString(),
        pin = pin,
        alias = alias,
        avatarType = avatarType ?: AvatarTypeEnum.BOY,
        userId = userId
    )

    data class Params(
        val alias: String,
        val pin: Int?,
        val avatarType: AvatarTypeEnum?
    )
}