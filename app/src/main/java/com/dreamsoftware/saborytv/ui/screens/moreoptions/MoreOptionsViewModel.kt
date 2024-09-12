package com.dreamsoftware.saborytv.ui.screens.moreoptions

import com.dreamsoftware.saborytv.domain.usecase.AddFavoriteRecipeUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetRecipeByIdUseCase
import com.dreamsoftware.saborytv.domain.usecase.RemoveFavoriteRecipeUseCase
import com.dreamsoftware.saborytv.domain.usecase.VerifyRecipeInFavoritesUseCase
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreOptionsViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val addFavoriteRecipeUseCase: AddFavoriteRecipeUseCase,
    private val removeFavoriteRecipeUseCase: RemoveFavoriteRecipeUseCase,
    private val verifyRecipeInFavoritesUseCase: VerifyRecipeInFavoritesUseCase
) : FudgeTvViewModel<MoreOptionsUiState, MoreOptionsSideEffects>(),
    MoreOptionsScreenActionListener {

    override fun onGetDefaultState(): MoreOptionsUiState = MoreOptionsUiState()

    fun fetchData(id: String) {
        executeUseCaseWithParams(
            useCase = verifyRecipeInFavoritesUseCase,
            params = VerifyRecipeInFavoritesUseCase.Params(recipeId = id),
            onSuccess = ::onVerifyRecipeInFavoritesCompleted
        )
        executeUseCaseWithParams(
            useCase = getRecipeByIdUseCase,
            params = GetRecipeByIdUseCase.Params(id),
            onSuccess = ::onGetRecipeProgramByIdSuccessfully
        )
    }

    private fun onGetRecipeProgramByIdSuccessfully(recipe: RecipeBO) {
        updateState { it.copy(recipe = recipe) }
    }

    override fun onBackPressed() {
        launchSideEffect(MoreOptionsSideEffects.CloseMoreInfoDetail)
    }

    override fun onRecipeOpened() {
        uiState.value.recipe?.let {
            launchSideEffect(
                MoreOptionsSideEffects.PlayRecipeProgram(id = it.id)
            )
        }
    }

    override fun onFavouriteClicked() {
        with(uiState.value) {
            recipe?.let {
                if (isFavorite) {
                    removeRecipeFromFavorites(id = it.id)
                } else {
                    addRecipeToFavorites(id = it.id)
                }
            }
        }
    }

    override fun onOpenChefProfileDetail() {
        uiState.value.recipe?.let {
            launchSideEffect(
                MoreOptionsSideEffects.OpenChefProfileDetail(id = it.chefProfileId)
            )
        }
    }

    private fun removeRecipeFromFavorites(id: String) {
        executeUseCaseWithParams(
            useCase = removeFavoriteRecipeUseCase,
            params = RemoveFavoriteRecipeUseCase.Params(
                recipesId = id
            ),
            onSuccess = ::onChangeFavoriteRecipeCompleted
        )
    }

    private fun addRecipeToFavorites(id: String) {
        executeUseCaseWithParams(
            useCase = addFavoriteRecipeUseCase,
            params = AddFavoriteRecipeUseCase.Params(id = id),
            onSuccess = ::onChangeFavoriteRecipeCompleted
        )
    }

    private fun onChangeFavoriteRecipeCompleted(isSuccess: Boolean) {
        if (isSuccess) {
            updateState { it.copy(isFavorite = !it.isFavorite) }
        }
    }

    private fun onVerifyRecipeInFavoritesCompleted(isFavorite: Boolean) {
        updateState { it.copy(isFavorite = isFavorite) }
    }
}

data class MoreOptionsUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val recipe: RecipeBO? = null,
    val isFavorite: Boolean = false
) : UiState<MoreOptionsUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): MoreOptionsUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface MoreOptionsSideEffects : SideEffect {
    data class PlayRecipeProgram(val id: String) : MoreOptionsSideEffects
    data class OpenChefProfileDetail(val id: String) : MoreOptionsSideEffects
    data object CloseMoreInfoDetail : MoreOptionsSideEffects
}