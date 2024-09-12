package com.dreamsoftware.saborytv.ui.screens.favorites

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onOpenRecipeDetail: (id: String) -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { FavoritesUiState() },
        onSideEffect = {
            when(it) {
                is FavoritesSideEffects.OpenRecipeProgramDetail -> onOpenRecipeDetail(it.id)
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