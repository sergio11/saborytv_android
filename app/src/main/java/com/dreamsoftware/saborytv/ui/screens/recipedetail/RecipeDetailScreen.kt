package com.dreamsoftware.saborytv.ui.screens.recipedetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class RecipeDetailScreenArgs(
    val id: String
)

@Composable
fun RecipeDetailScreen(
    viewModel: RecipeDetailViewModel = hiltViewModel(),
    args: RecipeDetailScreenArgs,
    onPlayRecipeProgram: (String) -> Unit,
    onOpenMoreDetails: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { RecipeDetailUiState() },
        onSideEffect = {
            when(it) {
                is RecipeDetailSideEffects.PlayingRecipeProgram -> onPlayRecipeProgram(it.id)
                is RecipeDetailSideEffects.OpenMoreInfo -> onOpenMoreDetails(it.id)
            }
        },
        onInit = {
            fetchData(id = args.id)
        }
    ) { uiState ->
        RecipeDetailScreenContent(
            state = uiState,
            actionListener = viewModel
        )
    }
}