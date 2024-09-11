package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.repository.IRecipesRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams
import com.dreamsoftware.saborytv.domain.model.RecipeBO

class GetRecipeByIdUseCase(
    private val recipesRepository: IRecipesRepository
) : FudgeTvUseCaseWithParams<GetRecipeByIdUseCase.Params, RecipeBO>() {

    override suspend fun onExecuted(params: Params): RecipeBO = with(params) {
        recipesRepository.getRecipeById(id = id)
    }

    data class Params(
        val id: String,
    )
}