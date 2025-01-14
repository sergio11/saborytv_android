package com.dreamsoftware.saborytv.ui.screens.profiles.selector

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dreamsoftware.fudge.component.FudgeTvScreen

@Composable
fun ProfileSelectorScreen(
    viewModel: ProfileSelectorViewModel = hiltViewModel(),
    onProfileSelected: () -> Unit,
    onProfileLocked: (String) -> Unit,
    onGoToAddProfile: () -> Unit,
    onGoToProfileManagement: () -> Unit
) {
    FudgeTvScreen(
        viewModel = viewModel,
        onInit = {  loadProfiles() },
        onInitialUiState = { ProfileSelectorUiState() },
        onSideEffect = {
            when(it) {
                is ProfileSelectorSideEffects.ProfileLocked -> onProfileLocked(it.profileId)
                ProfileSelectorSideEffects.ProfileSelected -> onProfileSelected()
                ProfileSelectorSideEffects.AddNewProfile -> onGoToAddProfile()
                ProfileSelectorSideEffects.ConfigureProfiles -> onGoToProfileManagement()
            }
        }
    ) { uiState ->
        ProfileSelectorContent(
            uiState = uiState,
            actionListener = viewModel
        )
    }
}