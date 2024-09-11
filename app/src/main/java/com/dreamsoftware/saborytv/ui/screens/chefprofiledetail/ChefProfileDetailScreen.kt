package com.dreamsoftware.saborytv.ui.screens.chefprofiledetail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class ChefProfileDetailScreenArgs(
    val id: String
)

@Composable
fun ChefProfileDetailScreen(
    viewModel: ChefProfileDetailScreenViewModel = hiltViewModel(),
    args: ChefProfileDetailScreenArgs,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { ChefProfileDetailUiState() },
        onSideEffect = {
            when(it) {
                ChefProfileDetailSideEffects.CloseDetail -> onBackPressed()
            }
        },
        onInit = {
            fetchData(args.id)
        }
    ) { uiState ->
        ChefProfileDetailScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}