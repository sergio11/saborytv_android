package com.dreamsoftware.saborytv.ui.screens.favorites

import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.IFudgeTvErrorMapper
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.saborytv.di.FavoritesScreenErrorMapper
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import com.dreamsoftware.saborytv.domain.usecase.GetFavoritesRecipesByUserUseCase
import com.dreamsoftware.saborytv.domain.usecase.RemoveFavoriteRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesRecipesByUserUseCase: GetFavoritesRecipesByUserUseCase,
    private val removeFavoriteRecipeUseCase: RemoveFavoriteRecipeUseCase,
    @FavoritesScreenErrorMapper private val errorMapper: IFudgeTvErrorMapper,
) : FudgeTvViewModel<FavoritesUiState, FavoritesSideEffects>(), FavoritesScreenActionListener {

    override fun onGetDefaultState(): FavoritesUiState = FavoritesUiState()

    fun fetchData() {
        executeUseCase(
            useCase = getFavoritesRecipesByUserUseCase,
            onSuccess = ::onGetFavoritesRecipesSuccessfully,
            onMapExceptionToState = ::onMapExceptionToState
        )
    }

    override fun onRecipeSelected(recipe: RecipeBO) {
        updateState { it.copy(recipeProgramSelected = recipe) }
    }

    override fun onRecipeStarted(id: String) {
        uiState.value.recipeProgramSelected?.let { trainingProgramSelected ->
            updateState { it.copy(recipeProgramSelected = null) }
            launchSideEffect(FavoritesSideEffects.OpenRecipeProgramDetail(id = trainingProgramSelected.id))
        }
    }

    override fun onRecipeRemovedFromFavorites(id: String) {
        executeUseCaseWithParams(
            useCase = removeFavoriteRecipeUseCase,
            params = RemoveFavoriteRecipeUseCase.Params(recipesId = id),
            onSuccess = ::onRecipeProgramRemovedFromFavoritesCompletedSuccessfully
        )
    }

    override fun onDismissRequest() {
        updateState { it.copy(recipeProgramSelected = null) }
    }

    private fun onGetFavoritesRecipesSuccessfully(recipeProgramList: List<RecipeBO>) {
        updateState { it.copy(recipes = recipeProgramList) }
    }

    private fun onRecipeProgramRemovedFromFavoritesCompletedSuccessfully(isRemoved: Boolean) {
        if(isRemoved) {
            updateState { it.copy(
                recipes = it.recipes.filterNot { training -> training.id == it.recipeProgramSelected?.id },
                recipeProgramSelected = null
            ) }
        }
    }

    private fun onMapExceptionToState(ex: Exception, uiState: FavoritesUiState) =
        uiState.copy(
            isLoading = false,
            errorMessage = errorMapper.mapToMessage(ex)
        )
}

data class FavoritesUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val recipes: List<RecipeBO> = emptyList(),
    val recipeProgramSelected: RecipeBO? = null
): UiState<FavoritesUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): FavoritesUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface FavoritesSideEffects: SideEffect {
    data class OpenRecipeProgramDetail(val id: String): FavoritesSideEffects
}