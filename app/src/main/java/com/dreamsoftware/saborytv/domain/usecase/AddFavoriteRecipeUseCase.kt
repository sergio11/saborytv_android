package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.AddFavoriteRecipeBO
import com.dreamsoftware.saborytv.domain.model.TrainingTypeEnum
import com.dreamsoftware.saborytv.domain.repository.IProfilesRepository
import com.dreamsoftware.saborytv.domain.repository.IRecipesRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class AddFavoriteRecipeUseCase(
    private val userRepository: IUserRepository,
    private val profileRepository: IProfilesRepository,
    private val trainingRepository: IRecipesRepository
) : FudgeTvUseCaseWithParams<AddFavoriteRecipeUseCase.Params, Boolean>() {

    override suspend fun onExecuted(params: Params) = with(params) {
        val currentUserUid = userRepository.getAuthenticatedUid()
        val profileSelected = profileRepository.getProfileSelectedByUser(currentUserUid)
        toAddFavoriteTrainingBO(profileSelected.uuid)
            .let { trainingRepository.addFavoriteRecipe(it) }
    }

    private fun Params.toAddFavoriteTrainingBO(profileId: String) = AddFavoriteRecipeBO(
        profileId = profileId,
        id = trainingId,
        type = trainingType
    )

    data class Params(
        val trainingId: String,
        val trainingType: TrainingTypeEnum
    )
}