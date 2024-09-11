package com.dreamsoftware.saborytv.ui.screens.moreoptions

import com.dreamsoftware.saborytv.domain.model.ITrainingProgramBO
import com.dreamsoftware.saborytv.domain.model.TrainingTypeEnum
import com.dreamsoftware.saborytv.domain.usecase.AddFavoriteRecipeUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetRecipeByIdUseCase
import com.dreamsoftware.saborytv.domain.usecase.RemoveFavoriteRecipeUseCase
import com.dreamsoftware.saborytv.domain.usecase.VerifyRecipeInFavoritesUseCase
import com.dreamsoftware.saborytv.ui.utils.toTrainingType
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreOptionsViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val addFavoriteRecipeUseCase: AddFavoriteRecipeUseCase,
    private val removeFavoriteRecipeUseCase: RemoveFavoriteRecipeUseCase,
    private val verifyRecipeInFavoritesUseCase: VerifyRecipeInFavoritesUseCase
) : FudgeTvViewModel<MoreOptionsUiState, MoreOptionsSideEffects>(), MoreOptionsScreenActionListener {

    override fun onGetDefaultState(): MoreOptionsUiState = MoreOptionsUiState()

    fun fetchData(id: String, type: TrainingTypeEnum) {
        executeUseCaseWithParams(
            useCase = verifyRecipeInFavoritesUseCase,
            params = VerifyRecipeInFavoritesUseCase.Params(trainingId = id),
            onSuccess = ::onVerifyTrainingInFavoritesCompleted
        )
        executeUseCaseWithParams(
            useCase = getRecipeByIdUseCase,
            params = GetRecipeByIdUseCase.Params(id, type),
            onSuccess = ::onGetTrainingProgramByIdSuccessfully
        )
    }

    private fun onGetTrainingProgramByIdSuccessfully(trainingProgram: ITrainingProgramBO) {
        updateState { it.copy(trainingProgram = trainingProgram) }
    }

    override fun onBackPressed() {
        launchSideEffect(MoreOptionsSideEffects.ExitFromMoreDetail)
    }

    override fun onTrainingProgramOpened() {
        uiState.value.trainingProgram?.let {
            launchSideEffect(
                MoreOptionsSideEffects.PlayTrainingProgram(
                    id = it.id,
                    type = it.toTrainingType()
                )
            )
        }
    }

    override fun onFavouriteClicked() {
        with(uiState.value) {
            trainingProgram?.let {
                if (isFavorite) {
                    removeTrainingProgramFromFavorites(id = it.id)
                } else {
                    addTrainingProgramToFavorites(id = it.id, type = it.toTrainingType())
                }
            }
        }
    }

    override fun onOpenInstructorDetail() {
        uiState.value.trainingProgram?.let {
            launchSideEffect(
                MoreOptionsSideEffects.OpenInstructorDetail(id = it.instructorId)
            )
        }
    }

    override fun onPlayTrainingSong() {
        uiState.value.trainingProgram?.let {
            launchSideEffect(
                MoreOptionsSideEffects.PlayTrainingSong(songId = it.song)
            )
        }
    }

    private fun removeTrainingProgramFromFavorites(id: String) {
        executeUseCaseWithParams(
            useCase = removeFavoriteRecipeUseCase,
            params = RemoveFavoriteRecipeUseCase.Params(
                trainingId = id
            ),
            onSuccess = ::onChangeFavoriteTrainingCompleted
        )
    }

    private fun addTrainingProgramToFavorites(id: String, type: TrainingTypeEnum) {
        executeUseCaseWithParams(
            useCase = addFavoriteRecipeUseCase,
            params = AddFavoriteRecipeUseCase.Params(
                trainingId = id,
                trainingType = type
            ),
            onSuccess = ::onChangeFavoriteTrainingCompleted
        )
    }

    private fun onChangeFavoriteTrainingCompleted(isSuccess: Boolean) {
        if (isSuccess) {
            updateState { it.copy(isFavorite = !it.isFavorite) }
        }
    }

    private fun onVerifyTrainingInFavoritesCompleted(isFavorite: Boolean) {
        updateState { it.copy(isFavorite = isFavorite) }
    }
}

data class MoreOptionsUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val trainingProgram: ITrainingProgramBO? = null,
    val isFavorite: Boolean = false
) : UiState<MoreOptionsUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): MoreOptionsUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface MoreOptionsSideEffects : SideEffect {
    data class PlayTrainingProgram(val id: String, val type: TrainingTypeEnum) : MoreOptionsSideEffects
    data class PlayTrainingSong(val songId: String) : MoreOptionsSideEffects
    data class OpenInstructorDetail(val id: String): MoreOptionsSideEffects
    data object ExitFromMoreDetail: MoreOptionsSideEffects
}