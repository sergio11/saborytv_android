package com.dreamsoftware.saborytv.ui.screens.category

import com.dreamsoftware.saborytv.domain.model.CategoryBO
import com.dreamsoftware.saborytv.domain.usecase.GetCategoryByIdUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetRecipesByCategoryUseCase
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryDetailScreenViewModel @Inject constructor(
    private val getRecipesByCategoryUseCase: GetRecipesByCategoryUseCase,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase
) : FudgeTvViewModel<CategoryDetailUiState, CategoryDetailSideEffects>(), CategoryDetailActionListener {

    fun fetchData(id: String) {
        fetchCategoryDetail(id)
    }

    override fun onGetDefaultState(): CategoryDetailUiState = CategoryDetailUiState()

    override fun onRecipeProgramOpened(recipeBO: RecipeBO) {
        with(recipeBO) {
            launchSideEffect(
                CategoryDetailSideEffects.OpenRecipeProgramDetail(id = id)
            )
        }
    }

    private fun fetchRecipesByCategory(id: String) {
        executeUseCaseWithParams(
            useCase = getRecipesByCategoryUseCase,
            params = GetRecipesByCategoryUseCase.Params(id),
            onSuccess = ::onGetRecipesByCategorySuccessfully
        )
    }

    private fun fetchCategoryDetail(id: String) {
        executeUseCaseWithParams(
            useCase = getCategoryByIdUseCase,
            params = GetCategoryByIdUseCase.Params(id),
            onSuccess = ::onGetCategoryDetailSuccessfully
        )
    }

    private fun onGetRecipesByCategorySuccessfully(recipes: List<RecipeBO>) {
        updateState { it.copy(recipes = recipes) }
    }

    private fun onGetCategoryDetailSuccessfully(category: CategoryBO) {
        updateState { it.copy(category = category) }
        fetchRecipesByCategory(category.id)
    }
}

data class CategoryDetailUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val recipes: List<RecipeBO> = emptyList(),
    val category: CategoryBO? = null
): UiState<CategoryDetailUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): CategoryDetailUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface CategoryDetailSideEffects : SideEffect {
    data class OpenRecipeProgramDetail(val id: String): CategoryDetailSideEffects
}