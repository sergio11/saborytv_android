package com.dreamsoftware.saborytv.ui.screens.ingredients
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import com.dreamsoftware.saborytv.domain.usecase.GetRecipeByIdUseCase
import com.dreamsoftware.saborytv.ui.utils.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IngredientsDetailDetailViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
) : FudgeTvViewModel<IngredientsDetailUiState, IngredientsDetailSideEffects>(), IIngredientsDetailScreenActionListener {

    fun loadData(id: String) {
        executeUseCaseWithParams(
            useCase = getRecipeByIdUseCase,
            params = GetRecipeByIdUseCase.Params(id),
            onSuccess = ::onGetRecipeProgramByIdSuccessfully
        )
    }

    override fun onGetDefaultState(): IngredientsDetailUiState = IngredientsDetailUiState()

    private fun onGetRecipeProgramByIdSuccessfully(recipe: RecipeBO) {
        updateState {
            it.copy(
                recipeImageUrl = recipe.imageUrl,
                ingredients = recipe.ingredients
            )
        }
    }

    override fun onCompleted() {
        launchSideEffect(IngredientsDetailSideEffects.CloseIngredientsDetail)
    }
}

data class IngredientsDetailUiState(
    override var isLoading: Boolean = false,
    override var errorMessage: String? = null,
    val recipeImageUrl: String = String.EMPTY,
    val ingredients: List<String> = emptyList()
): UiState<IngredientsDetailUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): IngredientsDetailUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface IngredientsDetailSideEffects: SideEffect {
    data object CloseIngredientsDetail: IngredientsDetailSideEffects
}