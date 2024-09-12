package com.dreamsoftware.saborytv.domain.usecase

import com.dreamsoftware.saborytv.domain.model.LanguageEnum
import com.dreamsoftware.saborytv.domain.model.SortTypeEnum
import com.dreamsoftware.saborytv.domain.model.RecipeFilterDataBO
import com.dreamsoftware.saborytv.domain.model.VideoLengthEnum
import com.dreamsoftware.saborytv.domain.repository.ISubscriptionsRepository
import com.dreamsoftware.saborytv.domain.repository.IRecipesRepository
import com.dreamsoftware.saborytv.domain.repository.IUserRepository
import com.dreamsoftware.fudge.core.FudgeTvUseCaseWithParams
import com.dreamsoftware.saborytv.domain.model.DifficultyEnum
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import com.dreamsoftware.saborytv.domain.model.RecipeTypeEnum

class GetRecipesByTypeUseCase(
    private val userRepository: IUserRepository,
    private val subscriptionsRepository: ISubscriptionsRepository,
    private val recipesRepository: IRecipesRepository
) : FudgeTvUseCaseWithParams<GetRecipesByTypeUseCase.Params, List<RecipeBO>>() {

    override suspend fun onExecuted(params: Params): List<RecipeBO> {
        val userUid = userRepository.getAuthenticatedUid()
        val hasActiveSubscription = subscriptionsRepository.hasActiveSubscription(userUid)
        return recipesRepository.getRecipes(
            filter = params.toRecipeFilterData(),
            includePremium = hasActiveSubscription
        ).toList()
    }

    private fun Params.toRecipeFilterData() = RecipeFilterDataBO(
        type = type,
        language = classLanguage,
        difficulty = difficulty,
        videoLength = videoLength,
        sortType = sortType,
        chefProfile = instructor
    )

    data class Params(
        val type: RecipeTypeEnum,
        val classLanguage: LanguageEnum,
        val difficulty: DifficultyEnum,
        val videoLength: VideoLengthEnum,
        val sortType: SortTypeEnum,
        val instructor: String
    )
}