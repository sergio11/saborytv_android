package com.dreamsoftware.saborytv.ui.screens.ingredients

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class IngredientsDetailScreenArgs(
    val id: String
)

@Composable
fun IngredientsDetailScreen(
    viewModel: IngredientsDetailDetailViewModel = hiltViewModel(),
    args: IngredientsDetailScreenArgs,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { IngredientsDetailUiState() },
        onSideEffect = {
            when(it) {
                IngredientsDetailSideEffects.CloseIngredientsDetail -> onBackPressed()
            }
        },
        onInit = {
            loadData(args.id)
        }
    ) { uiState ->
        IngredientsDetailScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}
