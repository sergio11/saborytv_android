package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.repository.IProfilesRepository
import com.dreamsoftware.saborytv.domain.repository.IRecipesRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class VerifyRecipeInFavoritesUseCase(
    private val userRepository: IUserRepository,
    private val profileRepository: IProfilesRepository,
    private val trainingRepository: IRecipesRepository
): FudgeTvUseCaseWithParams<VerifyRecipeInFavoritesUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        val userUid = userRepository.getAuthenticatedUid()
        val profileSelected = profileRepository.getProfileSelectedByUser(userUid)
        trainingRepository.hasRecipeInFavorites(
            profileId = profileSelected.uuid,
            recipeId = trainingId
        )
    }

    data class Params(
        val trainingId: String
    )
}