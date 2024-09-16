package com.dreamsoftware.saborytv.ui.screens.instructions

import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import com.dreamsoftware.saborytv.domain.usecase.GetRecipeByIdUseCase
import com.dreamsoftware.saborytv.ui.utils.EMPTY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InstructionsDetailDetailViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
) : FudgeTvViewModel<InstructionsDetailUiState, InstructionsDetailSideEffects>(), IIngredientsDetailScreenActionListener {

    fun loadData(id: String) {
        executeUseCaseWithParams(
            useCase = getRecipeByIdUseCase,
            params = GetRecipeByIdUseCase.Params(id),
            onSuccess = ::onGetRecipeProgramByIdSuccessfully
        )
    }

    override fun onGetDefaultState(): InstructionsDetailUiState = InstructionsDetailUiState()

    private fun onGetRecipeProgramByIdSuccessfully(recipe: RecipeBO) {
        updateState {
            it.copy(
                recipeImageUrl = recipe.imageUrl,
                instructions = recipe.instructions
            )
        }
    }

    override fun onCompleted() {
        launchSideEffect(InstructionsDetailSideEffects.CloseInstructionsDetail)
    }
}

data class InstructionsDetailUiState(
    override var isLoading: Boolean = false,
    override var errorMessage: String? = null,
    val recipeImageUrl: String = String.EMPTY,
    val instructions: List<String> = emptyList()
): UiState<InstructionsDetailUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): InstructionsDetailUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface InstructionsDetailSideEffects: SideEffect {
    data object CloseInstructionsDetail: InstructionsDetailSideEffects
}