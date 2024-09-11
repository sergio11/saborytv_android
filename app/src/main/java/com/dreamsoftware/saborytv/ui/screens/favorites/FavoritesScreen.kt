package com.dreamsoftware.saborytv.ui.screens.favorites

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onOpenTrainingProgramDetail: (id: String, type: TrainingTypeEnum) -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { FavoritesUiState() },
        onSideEffect = {
            when(it) {
                is FavoritesSideEffects.OpenRecipeProgramDetail -> onOpenTrainingProgramDetail(it.id, it.type)
            }
        },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        FavoritesScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}