package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.saborytv.domain.repository.IRecipesRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase
import com.dreamsoftware.saborytv.domain.model.RecipeBO

class GetRecipesRecommendedUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository,
    private val recipesRepository: IRecipesRepository
) : FudgeTvUseCase<List<RecipeBO>>() {
    override suspend fun onExecuted(): List<RecipeBO> {
        val userUid = userRepository.getAuthenticatedUid()
        val hasActiveSubscription = subscriptionsRepository.hasActiveSubscription(userUid)
        return recipesRepository.getRecipesRecommended(includePremium = hasActiveSubscription).toList()
    }
}