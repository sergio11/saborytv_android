package com.dreamsoftware.saborytv.ui.screens.recipedetail

import androidx.annotation.StringRes
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.domain.usecase.AddFavoriteRecipeUseCase
import com.dreamsoftware.saborytv.domain.usecase.GetRecipeByIdUseCase
import com.dreamsoftware.saborytv.domain.usecase.RemoveFavoriteRecipeUseCase
import com.dreamsoftware.saborytv.domain.usecase.VerifyRecipeInFavoritesUseCase
import com.dreamsoftware.saborytv.ui.screens.recipedetail.RecipeDetailUiState.RecipeInfoItem
import com.dreamsoftware.saborytv.ui.utils.EMPTY
import com.dreamsoftware.fudge.core.FudgeTvViewModel
import com.dreamsoftware.fudge.core.SideEffect
import com.dreamsoftware.fudge.core.UiState
import com.dreamsoftware.fudge.utils.IFudgeTvApplicationAware
import com.dreamsoftware.saborytv.domain.model.RecipeBO
import com.dreamsoftware.saborytv.domain.model.RecipeTypeEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val addFavoriteRecipeUseCase: AddFavoriteRecipeUseCase,
    private val removeFavoriteRecipeUseCase: RemoveFavoriteRecipeUseCase,
    private val verifyRecipeInFavoritesUseCase: VerifyRecipeInFavoritesUseCase,
    private val applicationAware: IFudgeTvApplicationAware
) : FudgeTvViewModel<RecipeDetailUiState, RecipeDetailSideEffects>(), RecipeDetailScreenActionListener {

    override fun onGetDefaultState(): RecipeDetailUiState = RecipeDetailUiState()

    fun fetchData(id: String) {
        executeUseCaseWithParams(
            useCase = verifyRecipeInFavoritesUseCase,
            params = VerifyRecipeInFavoritesUseCase.Params(recipeId = id),
            onSuccess = ::onVerifyRecipeInFavoritesCompleted
        )
        executeUseCaseWithParams(
            useCase = getRecipeByIdUseCase,
            params = GetRecipeByIdUseCase.Params(id),
            onSuccess = ::onGetRecipeByIdSuccessfully
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
                        recipesId = id
                    ),
                    onSuccess = ::onChangeFavoriteRecipeCompleted
                )
            } else {
                executeUseCaseWithParams(
                    useCase = addFavoriteRecipeUseCase,
                    params = AddFavoriteRecipeUseCase.Params(
                        id = id
                    ),
                    onSuccess = ::onChangeFavoriteRecipeCompleted
                )
            }
        }
    }

    private fun onChangeFavoriteRecipeCompleted(isSuccess: Boolean) {
        if(isSuccess) {
            updateState { it.copy(isFavorite = !it.isFavorite) }
        }
    }

    private fun onVerifyRecipeInFavoritesCompleted(isFavorite: Boolean) {
        updateState { it.copy(isFavorite = isFavorite) }
    }

    private fun onGetRecipeByIdSuccessfully(recipeBO: RecipeBO) {
        updateState {
            with(recipeBO) {
                it.copy(
                    subtitle = "$chefProfileName | ${type.value}",
                    title = title,
                    description = description,
                    id = id,
                    type = type,
                    itemsInfo = buildList {
                        add(RecipeInfoItem(info = applicationAware.getString(R.string.preparation_time_value, preparationTime), labelRes = R.string.length))
                        add(RecipeInfoItem(info = difficulty.value, labelRes = R.string.difficulty))
                        add(RecipeInfoItem(info = applicationAware.getString(R.string.serving_value, servings), labelRes = R.string.serving))
                        add(RecipeInfoItem(info = applicationAware.getString(R.string.ingredients_count_value, ingredients.size), labelRes = R.string.ingredients_count))
                        add(RecipeInfoItem(info = applicationAware.getString(R.string.steps_count_value, instructions.size), labelRes = R.string.steps_count))
                        add(RecipeInfoItem(info = country, labelRes = R.string.country_origin))
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
    val type: RecipeTypeEnum = RecipeTypeEnum.VEGETARIAN,
    val itemsInfo: List<RecipeInfoItem> = listOf(),
    val isFavorite: Boolean = false,
): UiState<RecipeDetailUiState>(isLoading, errorMessage) {

    override fun copyState(isLoading: Boolean, errorMessage: String?): RecipeDetailUiState =
        copy(isLoading = isLoading, errorMessage = errorMessage)

    data class RecipeInfoItem(
        val info: String = String.EMPTY,
        @StringRes val labelRes: Int
    )
}

sealed interface RecipeDetailSideEffects: SideEffect {
    data class PlayingRecipeProgram(val id: String): RecipeDetailSideEffects
    data class OpenMoreInfo(val id: String): RecipeDetailSideEffects
}
