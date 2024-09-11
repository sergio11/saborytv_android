package com.dreamsoftware.saborytv.ui.screens.favorites

import com.dreamsoftware.saborytv.di.FavoritesScreenErrorMapper
import com.dreamsoftware.saborytv.domain.model.ITrainingProgramBO
import com.dreamsoftware.saborytv.domain.model.TrainingTypeEnum
import com.dreamsoftware.saborytv.domain.usecase.GetFavoritesRecipesByUserUseCase
import com.dreamsoftware.saborytv.domain.usecase.RemoveFavoriteRecipeUseCase
import com.dreamsoftware.saborytv.ui.utils.toTrainingType
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.IFudgeTvErrorMapper
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
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
            onSuccess = ::onGetFavoritesWorkoutsSuccessfully,
            onMapExceptionToState = ::onMapExceptionToState
        )
    }

    override fun onTrainingProgramSelected(trainingProgram: ITrainingProgramBO) {
        updateState { it.copy(trainingProgramSelected = trainingProgram) }
    }

    override fun onTrainingProgramStarted(id: String) {
        uiState.value.trainingProgramSelected?.let { trainingProgramSelected ->
            updateState { it.copy(trainingProgramSelected = null) }
            launchSideEffect(FavoritesSideEffects.OpenTrainingProgramDetail(
                id = trainingProgramSelected.id,
                type = trainingProgramSelected.toTrainingType()
            ))
        }
    }

    override fun onTrainingProgramRemovedFromFavorites(id: String) {
        executeUseCaseWithParams(
            useCase = removeFavoriteRecipeUseCase,
            params = RemoveFavoriteRecipeUseCase.Params(trainingId = id),
            onSuccess = ::onTrainingProgramRemovedFromFavoritesCompletedSuccessfully
        )
    }

    override fun onDismissRequest() {
        updateState { it.copy(trainingProgramSelected = null) }
    }

    private fun onGetFavoritesWorkoutsSuccessfully(trainingProgramList: List<ITrainingProgramBO>) {
        updateState { it.copy(favoritesTrainings = trainingProgramList) }
    }

    private fun onTrainingProgramRemovedFromFavoritesCompletedSuccessfully(isRemoved: Boolean) {
        if(isRemoved) {
            updateState { it.copy(
                favoritesTrainings = it.favoritesTrainings.filterNot { training -> training.id == it.trainingProgramSelected?.id },
                trainingProgramSelected = null
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
    val favoritesTrainings: List<ITrainingProgramBO> = emptyList(),
    val trainingProgramSelected: ITrainingProgramBO? = null
): UiState<FavoritesUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): FavoritesUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface FavoritesSideEffects: SideEffect {
    data class OpenTrainingProgramDetail(val id: String, val type: TrainingTypeEnum): FavoritesSideEffects
}