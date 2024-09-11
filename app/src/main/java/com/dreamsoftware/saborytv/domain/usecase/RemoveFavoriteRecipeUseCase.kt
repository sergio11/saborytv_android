package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.repository.IRecipesRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class RemoveFavoriteRecipeUseCase(
    private val userRepository: IUserRepository,
    private val trainingRepository: IRecipesRepository
): FudgeTvUseCaseWithParams<RemoveFavoriteRecipeUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        trainingRepository.removeFavoriteRecipe(
            profileId = userRepository.getAuthenticatedUid(),
            recipeId = trainingId
        )
    }

    data class Params(
        val trainingId: String
    )
}