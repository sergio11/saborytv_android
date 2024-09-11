package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.saborytv.domain.repository.IRecipesRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams
import com.dreamsoftware.saborytv.domain.model.RecipeBO

class GetRecipesByCategoryUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository,
    private val recipesRepository: IRecipesRepository
) : FudgeTvUseCaseWithParams<GetRecipesByCategoryUseCase.Params, List<RecipeBO>>() {
    override suspend fun onExecuted(params: Params): List<RecipeBO> {
        val userUid = userRepository.getAuthenticatedUid()
        val hasActiveSubscription = subscriptionsRepository.hasActiveSubscription(userUid)
        return recipesRepository.getRecipesByCategory(
            id = params.id,
            includePremium = hasActiveSubscription
        ).toList()
    }

    data class Params(
        val id: String
    )
}