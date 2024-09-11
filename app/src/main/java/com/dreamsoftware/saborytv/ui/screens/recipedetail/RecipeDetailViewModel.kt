package com.dreamsoftware.saborytv.ui.screens.recipedetail

import androidx.annotation.StringRes
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.domain.model.SeriesBO
import com.dreamsoftware.saborytv.domain.usecase.AddFavoriteRecipeUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetRecipeByIdUseCase
import com.dreamsoftware.saborytv.domain.usecase.RemoveFavoriteRecipeUseCase
import com.dreamsoftware.saborytv.domain.usecase.VerifyRecipeInFavoritesUseCase
import com.dreamsoftware.saborytv.ui.screens.recipedetail.RecipeDetailUiState.RecipeInfoItem
import com.dreamsoftware.saborytv.ui.utils.EMPTY
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val addFavoriteRecipeUseCase: AddFavoriteRecipeUseCase,
    private val removeFavoriteRecipeUseCase: RemoveFavoriteRecipeUseCase,
    private val verifyRecipeInFavoritesUseCase: VerifyRecipeInFavoritesUseCase
) : FudgeTvViewModel<RecipeDetailUiState, RecipeDetailSideEffects>(), RecipeDetailScreenActionListener {

    override fun onGetDefaultState(): RecipeDetailUiState = RecipeDetailUiState()

    fun fetchData(id: String) {
        executeUseCaseWithParams(
            useCase = verifyRecipeInFavoritesUseCase,
            params = VerifyRecipeInFavoritesUseCase.Params(trainingId = id),
            onSuccess = ::onVerifyTrainingInFavoritesCompleted
        )
        executeUseCaseWithParams(
            useCase = getRecipeByIdUseCase,
            params = GetRecipeByIdUseCase.Params(id),
            onSuccess = ::onGetRecipeProgramByIdSuccessfully
        )
    }

    override fun onRecipeStarted() {
        with(uiState.value) {
            launchSideEffect(RecipeDetailSideEffects.PlayingRecipeProgram(id = id))
        }
    }

    override fun onRecipeMoreInfoRequested() {
        with(uiState.value) {
            launchSideEffect(RecipeDetailSideEffects.OpenMoreInfo(id = id))
        }
    }

    override fun onRecipeFavoriteClicked() {
        with(uiState.value) {
            if(isFavorite) {
                executeUseCaseWithParams(
                    useCase = removeFavoriteRecipeUseCase,
                    params = RemoveFavoriteRecipeUseCase.Params(
                        trainingId = id
                    ),
                    onSuccess = ::onChangeFavoriteTrainingCompleted
                )
            } else {
                executeUseCaseWithParams(
                    useCase = addFavoriteRecipeUseCase,
                    params = AddFavoriteRecipeUseCase.Params(
                        id = id
                    ),
                    onSuccess = ::onChangeFavoriteTrainingCompleted
                )
            }
        }
    }

    private fun onChangeFavoriteTrainingCompleted(isSuccess: Boolean) {
        if(isSuccess) {
            updateState { it.copy(isFavorite = !it.isFavorite) }
        }
    }

    private fun onVerifyTrainingInFavoritesCompleted(isFavorite: Boolean) {
        updateState { it.copy(isFavorite = isFavorite) }
    }

    private fun onGetRecipeProgramByIdSuccessfully(recipeBO: RecipeBO) {
        updateState {
            with(recipeBO) {
                it.copy(
                    subtitle = "$chefProfileName | ${type.value}",
                    title = title,
                    description = description,
                    id = id,
                    itemsInfo = buildList {
                        add(RecipeInfoItem(info = "$preparationTime min", labelRes = R.string.length))
                        add(RecipeInfoItem(info = difficulty.value, labelRes = R.string.intensity))
                        /*when(this@with) {
                            is SeriesBO -> {
                                add(RecipeInfoItem(info = numberOfWeeks.toString(), labelRes = R.string.week))
                                add(RecipeInfoItem(info = numberOfClasses.toString(), labelRes = R.string.classes))
                            }
                            is ChallengeBO -> {
                                add(RecipeInfoItem(info = numberOfDays.toString(), labelRes = R.string.days))
                            }
                        }*/
                    },
                    imageUrl = imageUrl
                )
            }
        }
    }
}

data class RecipeDetailUiState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val subtitle: String = String.EMPTY,
    val title: String = String.EMPTY,
    val description: String = String.EMPTY,
    val imageUrl: String = String.EMPTY,
    val id: String = String.EMPTY,
    val itemsInfo: List<RecipeInfoItem> = listOf(),
    val isFavorite: Boolean = false,
    val challengePages: List<TrainingDetailPages> = listOf(
        TrainingDetailPages.DetailDetails,
        TrainingDetailPages.DetailTabs
    )
): UiState<RecipeDetailUiState>(isLoading, errorMessage) {

    override fun copyState(isLoading: Boolean, errorMessage: String?): RecipeDetailUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)

    data class ChallengeWorkoutItemUiState(
        val id: String,
        val imageUrl: String,
        val title: String,
        val time: String,
        val typeText: String,
    )
    data class RecipeInfoItem(
        val info: String = String.EMPTY,
        @StringRes val labelRes: Int
    )
}

sealed class TrainingDetailPages {
    data object DetailDetails : TrainingDetailPages()
    data object DetailTabs : TrainingDetailPages()
}

sealed interface RecipeDetailSideEffects: SideEffect {
    data class PlayingRecipeProgram(val id: String): RecipeDetailSideEffects
    data class OpenMoreInfo(val id: String): RecipeDetailSideEffects
}
