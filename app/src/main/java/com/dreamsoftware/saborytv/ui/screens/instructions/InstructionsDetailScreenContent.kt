package com.dreamsoftware.saborytv.ui.screens.instructions

import androidx.compose.runtime.Composable
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.ui.core.components.SupportPreviewContentGrid

@Composable
fun InstructionsDetailScreenContent(
    uiState: InstructionsDetailUiState,
    actionListener: IIngredientsDetailScreenActionListener
) {
    with(uiState) {
        SupportPreviewContentGrid(
            mainTitleRes = R.string.instructions_main_title_text,
            secondaryTitleRes = R.string.instructions_main_secondary_text,
            confirmButtonTextRes = R.string.instructions_confirm_button_text,
            imageUrl = recipeImageUrl,
            contentList = instructions,
            onErrorAccepted = actionListener::onErrorMessageCleared,
            onAccepted = actionListener::onCompleted
        )
    }
}

