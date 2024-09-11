package com.dreamsoftware.saborytv.ui.screens.player

import com.dreamsoftware.saborytv.domain.usecase.GetRecipeByIdUseCase
import com.dreamsoftware.saborytv.ui.utils.EMPTY
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase
) : FudgeTvViewModel<VideoPlayerUiState, VideoPlayerSideEffects>() {

    override fun onGetDefaultState(): VideoPlayerUiState = VideoPlayerUiState()

    fun fetchData(id: String) {
        executeUseCaseWithParams(
            useCase = getRecipeByIdUseCase,
            params = GetRecipeByIdUseCase.Params(id),
            onSuccess = ::onGetRecipeProgramByIdSuccessfully
        )
    }

    private fun onGetRecipeProgramByIdSuccessfully(recipeBO: RecipeBO) {
        updateState {
            with(recipeBO) {
                it.copy(
                    title = title,
                    chefProfileName = chefProfileName,
                    videoUrl = videoUrl,
                    id = id
                )
            }
        }
    }
}

data class VideoPlayerUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val id: String = String.EMPTY,
    val videoUrl: String = String.EMPTY,
    val title: String = String.EMPTY,
    val chefProfileName: String = String.EMPTY
): UiState<VideoPlayerUiState>(isLoading, errorMessage) {
    override fun copyState(isLoading: Boolean, errorMessage: String?): VideoPlayerUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)
}

sealed interface VideoPlayerSideEffects: SideEffect