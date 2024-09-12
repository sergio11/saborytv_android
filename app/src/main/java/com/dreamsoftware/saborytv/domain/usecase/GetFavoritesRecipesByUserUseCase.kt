package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.repository.IProfilesRepository
import com.dreamsoftware.saborytv.domain.repository.IRecipesRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCase
import com.dreamsoftware.saborytv.domain.model.RecipeBO

class GetFavoritesRecipesByUserUseCase(
    private val userRepository: IUserRepository,
    private val profileRepository: IProfilesRepository,
    private val recipesRepository: IRecipesRepository
): FudgeTvUseCase<List<RecipeBO>>() {

    override suspend fun onExecuted(): List<RecipeBO> {
        val userUid = userRepository.getAuthenticatedUid()
        val profileSelected = profileRepository.getProfileSelectedByUser(userUid)
        return recipesRepository.getFavoritesRecipesByProfile(profileSelected.uuid)
    }
}