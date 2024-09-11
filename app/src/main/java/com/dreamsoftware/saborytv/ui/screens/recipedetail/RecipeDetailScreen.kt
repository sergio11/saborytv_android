package com.dreamsoftware.saborytv.ui.screens.recipedetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class RecipeDetailScreenArgs(
    val id: String,
    val type: TrainingTypeEnum
)

@Composable
fun RecipeDetailScreen(
    viewModel: RecipeDetailViewModel = hiltViewModel(),
    args: RecipeDetailScreenArgs,
    onPlayingTrainingProgram: (String, TrainingTypeEnum) -> Unit,
    onOpenMoreDetails: (String, TrainingTypeEnum) -> Unit,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { RecipeDetailUiState() },
        onSideEffect = {
            when(it) {
                is RecipeDetailSideEffects.PlayingRecipeProgram -> onPlayingTrainingProgram(it.id, it.type)
                is RecipeDetailSideEffects.OpenMoreInfo -> onOpenMoreDetails(it.id, it.type)
            }
        },
        onInit = {
            fetchData(
                id = args.id,
                type = args.type
            )
        }
    ) { uiState ->
        RecipeDetailScreenContent(
            state = uiState,
            actionListener = viewModel
        )
    }
}