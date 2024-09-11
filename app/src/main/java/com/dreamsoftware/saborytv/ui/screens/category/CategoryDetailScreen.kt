package com.dreamsoftware.saborytv.ui.screens.category

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class CategoryDetailScreenArgs(
    val id: String
)

@Composable
fun CategoryDetailScreen(
    viewModel: CategoryDetailScreenViewModel = hiltViewModel(),
    args: CategoryDetailScreenArgs,
    onOpenTrainingProgramDetail: (String, TrainingTypeEnum) -> Unit,
    onBackPressed: () -> Unit
) {
   FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { CategoryDetailUiState() },
        onSideEffect = {
            when(it) {
                is CategoryDetailSideEffects.OpenRecipeProgramDetail -> onOpenTrainingProgramDetail(it.id, it.type)
            }
        },
        onInit = {
            fetchData(args.id)
        }
    ) { uiState ->
        CategoryDetailScreenContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}