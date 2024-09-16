package com.dreamsoftware.saborytv.ui.screens.moreoptions

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class MoreOptionsScreenArgs(
    val id: String
)

@Composable
fun MoreOptionsScreen(
    viewModel: MoreOptionsViewModel = hiltViewModel(),
    args: MoreOptionsScreenArgs,
    onBackPressed: () -> Unit,
    onOpenChefProfileDetail: (id: String) -> Unit,
    onOpenRecipeIngredients: (id: String) -> Unit,
    onOpenRecipeInstructions: (id: String) -> Unit,
    onPlayRecipeProgram: (id: String) -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { MoreOptionsUiState() },
        onSideEffect = {
            when(it) {
                MoreOptionsSideEffects.CloseMoreInfoDetail -> onBackPressed()
                is MoreOptionsSideEffects.PlayRecipeProgram -> onPlayRecipeProgram(it.id)
                is MoreOptionsSideEffects.OpenChefProfileDetail -> onOpenChefProfileDetail(it.id)
                is MoreOptionsSideEffects.OpenRecipeIngredients -> onOpenRecipeIngredients(it.id)
                is MoreOptionsSideEffects.OpenRecipeInstructions -> onOpenRecipeInstructions(it.id)
            }
        },
        onInit = {
            with(args) {
                fetchData(id = id)
            }
        }
    ) { uiState ->
        MoreOptionsScreenContent(
            state = uiState,
            actionListener = viewModel
        )
    }
}

