package com.dreamsoftware.saborytv.ui.screens.home

import com.dreamsoftware.saborytv.domain.model.CategoryBO
import com.dreamsoftware.saborytv.domain.model.ITrainingProgramBO
import com.dreamsoftware.saborytv.domain.model.TrainingTypeEnum
import com.dreamsoftware.saborytv.domain.usecase.GetCategoriesUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetFeaturedTrainingsUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetTrainingsRecommendedUseCase
import com.dreamsoftware.saborytv.domain.usecase.HasActiveSubscriptionUseCase
import com.dreamsoftware.saborytv.ui.utils.toTrainingType
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeaturedTrainingsUseCase: GetFeaturedTrainingsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getTrainingsRecommendedUseCase: GetTrainingsRecommendedUseCase,
    private val hasActiveSubscriptionUseCase: HasActiveSubscriptionUseCase
) : FudgeTvViewModel<HomeUiState, HomeSideEffects>(), HomeScreenActionListener {

    override fun onGetDefaultState(): HomeUiState = HomeUiState()

    fun fetchData() {
        fetchFeaturedTrainings()
        fetchCategories()
        fetchTrainingsRecommended()
        verifyHasActiveSubscription()
    }

    private fun fetchFeaturedTrainings() {
        executeUseCase(useCase = getFeaturedTrainingsUseCase, onSuccess = ::onGetFeaturedTrainingsSuccessfully)
    }

    private fun fetchCategories() {
        executeUseCase(useCase = getCategoriesUseCase, onSuccess = ::onGetCategoriesSuccessfully)
    }

    private fun fetchTrainingsRecommended() {
        executeUseCase(useCase = getTrainingsRecommendedUseCase, onSuccess = ::onGetTrainingsRecommendedSuccessfully)
    }

    private fun verifyHasActiveSubscription() {
        executeUseCase(useCase = hasActiveSubscriptionUseCase, onSuccess = ::onVerifyHasActiveSubscriptionCompleted)
    }

    private fun onGetFeaturedTrainingsSuccessfully(trainings: List<ITrainingProgramBO>) {
        updateState { it.copy(featuredTrainings = trainings) }
    }

    private fun onVerifyHasActiveSubscriptionCompleted(hasActiveSubscription: Boolean){
        if(!hasActiveSubscription) {
            launchSideEffect(HomeSideEffects.NoActivePremiumSubscription)
        }
    }

    private fun onGetCategoriesSuccessfully(categories: List<CategoryBO>) {
        updateState { it.copy(categories = categories) }
    }

    private fun onGetTrainingsRecommendedSuccessfully(trainingsRecommended: List<ITrainingProgramBO>) {
        updateState { it.copy(recommended = trainingsRecommended) }
    }

    override fun onOpenTrainingProgram(trainingProgram: ITrainingProgramBO) {
        with(trainingProgram) {
            launchSideEffect(HomeSideEffects.OpenTrainingProgram(
                id = id,
                type = toTrainingType()
            ))
        }
    }

    override fun onCategorySelected(categoryId: String) {
        launchSideEffect(HomeSideEffects.OpenTrainingCategory(categoryId))
    }
}

data class HomeUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val categories: List<CategoryBO> = listOf(),
    val featuredTrainings: List<ITrainingProgramBO> = emptyList(),
    val recommended: List<ITrainingProgramBO> = listOf()
): UiState<HomeUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): HomeUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface HomeSideEffects: SideEffect {
    data class OpenTrainingProgram(val id: String, val type: TrainingTypeEnum): HomeSideEffects
    data class OpenTrainingCategory(val categoryId: String): HomeSideEffects
    data object NoActivePremiumSubscription: HomeSideEffects
}