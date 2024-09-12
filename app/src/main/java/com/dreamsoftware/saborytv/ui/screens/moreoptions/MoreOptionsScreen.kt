package com.dreamsoftware.saborytv.ui.screens.moreoptions

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class MoreOptionsScreenArgs(
    val id: String,
    val type: TrainingTypeEnum
)

@Composable
fun MoreOptionsScreen(
    viewModel: MoreOptionsViewModel = hiltViewModel(),
    args: MoreOptionsScreenArgs,
    onBackPressed: () -> Unit,
    onOpenInstructorDetail: (id: String) -> Unit,
    onPlayTrainingProgram: (id: String, type: TrainingTypeEnum) -> Unit,
    onPlayTrainingSong: (songId: String) -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { MoreOptionsUiState() },
        onSideEffect = {
            when(it) {
                MoreOptionsSideEffects.CloseMoreInfoDetail -> onBackPressed()
                is MoreOptionsSideEffects.PlayRecipeProgram -> onPlayTrainingProgram(it.id, it.type)
                is MoreOptionsSideEffects.PlayTrainingSong -> onPlayTrainingSong(it.songId)
                is MoreOptionsSideEffects.OpenChefProfileDetail -> onOpenInstructorDetail(it.id)
            }
        },
        onInit = {
            with(args) {
                fetchData(id = id, type = type)
            }
        }
    ) { uiState ->
        MoreOptionsScreenContent(
            state = uiState,
            actionListener = viewModel
        )
    }
}

