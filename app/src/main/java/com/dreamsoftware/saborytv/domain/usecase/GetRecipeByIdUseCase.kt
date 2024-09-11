package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.ITrainingProgramBO
import com.dreamsoftware.saborytv.domain.model.TrainingTypeEnum
import com.dreamsoftware.saborytv.domain.repository.IRecipesRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams

class GetRecipeByIdUseCase(
    private val trainingRepository: IRecipesRepository
) : FudgeTvUseCaseWithParams<GetRecipeByIdUseCase.Params, ITrainingProgramBO>() {

    override suspend fun onExecuted(params: Params): ITrainingProgramBO = with(params) {
        trainingRepository.getRecipeById(id = id, type = type)
    }

    data class Params(
        val id: String,
        val type: TrainingTypeEnum
    )
}