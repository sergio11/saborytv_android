package com.dreamsoftware.saborytv.ui.screens.profiles.management

import androidx.compose.runtime.Composable
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.fudge.component.profiles.FudgeTvProfileScreenContent
import com.dreamsoftware.fudge.component.profiles.FudgeTvProfileSelector

@Composable
internal fun ProfilesManagementScreenContent(
    uiState: ProfilesManagementUiState,
    onCompletePressed: () -> Unit,
    onProfileSelected: (profileId: String) -> Unit,
    onErrorAccepted: () -> Unit
) {
    with(MaterialTheme.colorScheme) {
        with(uiState) {
            FudgeTvProfileScreenContent(
                isLoading = isLoading,
                error = errorMessage,
                mainLogoRes = R.drawable.main_logo,
                mainLogoInverseRes = R.drawable.main_logo_inverse,
                mainColor = primary,
                secondaryColor = primaryContainer,
                ternaryColor = onPrimary,
                loadingTitleRes = R.string.generic_progress_dialog_title,
                loadingDescriptionRes = R.string.generic_progress_dialog_description,
                mainTitleRes = R.string.profiles_management_main_title,
                secondaryTitleRes = R.string.profiles_management_main_description,
                primaryOptionTextRes = R.string.profiles_management_form_confirm_button_text,
                onPrimaryOptionPressed = onCompletePressed,
                onErrorAccepted = onErrorAccepted
            ) {
                FudgeTvProfileSelector(
                    profiles = uiState.profiles,
                    editMode = true,
                    onProfileSelected = onProfileSelected
                )
            }
        }
    }
}