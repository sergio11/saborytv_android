package com.dreamsoftware.saborytv.ui.screens.recipes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

@Composable
fun RecipesScreen(
    viewModel: RecipesViewModel = hiltViewModel(),
    onGoToRecipeDetail: (String) -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onInitialUiState = { RecipesUiState() },
        onSideEffect = {
            when(it) {
                is RecipesSideEffects.OpenRecipeProgramDetail -> onGoToRecipeDetail(it.id)
            }
        },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        RecipesScreenContent(
            state = uiState,
            actionListener = viewModel
        )
    }
}


