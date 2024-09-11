package com.dreamsoftware.saborytv.ui.screens.recipes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.saborytv.domain.model.TrainingTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvScreen

@Composable
fun RecipesScreen(
    viewModel: RecipesViewModel = hiltViewModel(),
    onGoToRecipeDetail: (String, TrainingTypeEnum) -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onInitialUiState = { TrainingUiState() },
        onSideEffect = {
            when(it) {
                is TrainingSideEffects.OpenTrainingProgramDetail -> onGoToRecipeDetail(it.id, it.type)
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


