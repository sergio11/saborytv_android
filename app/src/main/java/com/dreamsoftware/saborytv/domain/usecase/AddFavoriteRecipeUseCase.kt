package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.AddFavoriteRecipeBO
import com.dreamsoftware.saborytv.domain.repository.IProfilesRepository
import com.dreamsoftware.saborytv.domain.repository.IRecipesRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class AddFavoriteRecipeUseCase(
    private val userRepository: IUserRepository,
    private val profileRepository: IProfilesRepository,
    private val recipesRepository: IRecipesRepository
) : FudgeTvUseCaseWithParams<AddFavoriteRecipeUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        val currentUserUid = userRepository.getAuthenticatedUid()
        val profileSelected = profileRepository.getProfileSelectedByUser(currentUserUid)
        toAddFavoriteRecipeBO(profileSelected.uuid)
            .let { recipesRepository.addFavoriteRecipe(it) }
    }

    private fun Params.toAddFavoriteRecipeBO(profileId: String) = AddFavoriteRecipeBO(
        profileId = profileId,
        id = id,
    )

    data class Params(
        val id: String
    )
}