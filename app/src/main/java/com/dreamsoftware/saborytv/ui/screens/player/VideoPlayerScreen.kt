package com.dreamsoftware.saborytv.ui.screens.player

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

data class VideoPlayerScreenArgs(
    val id: String,
    val type: TrainingTypeEnum
)

@Composable
fun VideoPlayerScreen(
    viewModel: VideoPlayerViewModel = hiltViewModel(),
    args: VideoPlayerScreenArgs,
    onBackPressed: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onBackPressed = onBackPressed,
        onInitialUiState = { VideoPlayerUiState() },
        onInit = {
            with(args) {
                fetchData(id = id, type = type)
            }
        }
    ) { uiState ->
        VideoPlayerScreenContent(state = uiState)
    }
}