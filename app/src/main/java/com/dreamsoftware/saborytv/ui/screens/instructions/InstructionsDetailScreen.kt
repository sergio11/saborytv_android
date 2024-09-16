package com.dreamsoftware.saborytv.ui.screens.instructions

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class InstructionsDetailScreenArgs(
    val id: String
)

@Composable
fun InstructionsDetailScreen(
    viewModel: InstructionsDetailDetailViewModel = hiltViewModel(),
    args: InstructionsDetailScreenArgs,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { InstructionsDetailUiState() },
        onSideEffect = {
            when(it) {
                InstructionsDetailSideEffects.CloseInstructionsDetail -> onBackPressed()
            }
        },
        onInit = {
            loadData(args.id)
        }
    ) { uiState ->
        InstructionsDetailScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}
