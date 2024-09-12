package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.repository.IRecipesRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class RemoveFavoriteRecipeUseCase(
    private val userRepository: IUserRepository,
    private val recipesRepository: IRecipesRepository
): FudgeTvUseCaseWithParams<RemoveFavoriteRecipeUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        recipesRepository.removeFavoriteRecipe(
            profileId = userRepository.getAuthenticatedUid(),
            recipeId = recipesId
        )
    }

    data class Params(
        val recipesId: String
    )
}