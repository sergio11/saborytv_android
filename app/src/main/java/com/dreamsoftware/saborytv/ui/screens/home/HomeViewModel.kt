package com.dreamsoftware.saborytv.ui.screens.home

import androidx.lifecycle.SavedStateHandle
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.saborytv.domain.model.CategoryBO
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import com.dreamsoftware.saborytv.domain.usecase.GetCategoriesUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetFeaturedRecipesUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetRecipesRecommendedUseCase
import com.dreamsoftware.saborytv.domain.usecase.HasActiveSubscriptionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFeaturedRecipesUseCase: GetFeaturedRecipesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getRecipesRecommendedUseCase: GetRecipesRecommendedUseCase,
    private val hasActiveSubscriptionUseCase: HasActiveSubscriptionUseCase,
    private val savedStateHandle: SavedStateHandle
) : FudgeTvViewModel<HomeUiState, HomeSideEffects>(), HomeScreenActionListener {

    companion object {
        private const val KEY_SUBSCRIPTION_VERIFIED = "subscription_verified"
    }
    private var subscriptionAlreadyVerified: Boolean
        get() = savedStateHandle[KEY_SUBSCRIPTION_VERIFIED] ?: false
        set(value) {
            savedStateHandle[KEY_SUBSCRIPTION_VERIFIED] = value
        }

    override fun onGetDefaultState(): HomeUiState = HomeUiState()

    fun fetchData() {
        fetchFeaturedRecipes()
        fetchCategories()
        fetchRecipesRecommended()
        if(!subscriptionAlreadyVerified) {
            verifyHasActiveSubscription()
        }
    }

    private fun fetchFeaturedRecipes() {
        executeUseCase(useCase = getFeaturedRecipesUseCase, onSuccess = ::onGetFeaturedRecipesSuccessfully)
    }

    private fun fetchCategories() {
        executeUseCase(useCase = getCategoriesUseCase, onSuccess = ::onGetCategoriesSuccessfully)
    }

    private fun fetchRecipesRecommended() {
        executeUseCase(useCase = getRecipesRecommendedUseCase, onSuccess = ::onGetRecipesRecommendedSuccessfully)
    }

    private fun verifyHasActiveSubscription() {
        executeUseCase(useCase = hasActiveSubscriptionUseCase, onSuccess = ::onVerifyHasActiveSubscriptionCompleted)
    }

    private fun onGetFeaturedRecipesSuccessfully(recipes: List<RecipeBO>) {
        updateState { it.copy(featuredRecipes = recipes) }
    }

    private fun onVerifyHasActiveSubscriptionCompleted(hasActiveSubscription: Boolean){
        if(!hasActiveSubscription) {
            launchSideEffect(HomeSideEffects.NoActivePremiumSubscription)
        }
        subscriptionAlreadyVerified = true
    }

    private fun onGetCategoriesSuccessfully(categories: List<CategoryBO>) {
        updateState { it.copy(categories = categories) }
    }

    private fun onGetRecipesRecommendedSuccessfully(recipesRecommended: List<RecipeBO>) {
        updateState { it.copy(recommended = recipesRecommended) }
    }

    override fun onOpenRecipeDetail(recipe: RecipeBO) {
        with(recipe) {
            launchSideEffect(HomeSideEffects.OpenRecipeDetail(id = id))
        }
    }

    override fun onCategorySelected(categoryId: String) {
        launchSideEffect(HomeSideEffects.OpenRecipesCategory(categoryId))
    }
}

data class HomeUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val categories: List<CategoryBO> = listOf(),
    val featuredRecipes: List<RecipeBO> = emptyList(),
    val recommended: List<RecipeBO> = listOf()
): UiState<HomeUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): HomeUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface HomeSideEffects: SideEffect {
    data class OpenRecipeDetail(val id: String): HomeSideEffects
    data class OpenRecipesCategory(val categoryId: String): HomeSideEffects
    data object NoActivePremiumSubscription: HomeSideEffects
}