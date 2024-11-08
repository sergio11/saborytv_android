package com.dreamsoftware.saborytv.ui.screens.ingredients

import androidx.compose.runtime.Composable
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.ui.core.components.SupportPreviewContentGrid

@Composable
fun IngredientsDetailScreenContent(
    uiState: IngredientsDetailUiState,
    actionListener: IIngredientsDetailScreenActionListener
) {
    with(uiState) {
        SupportPreviewContentGrid(
            mainTitleRes = R.string.ingredients_main_title_text,
            secondaryTitleRes = R.string.ingredients_main_secondary_text,
            confirmButtonTextRes = R.string.ingredients_confirm_button_text,
            imageUrl = recipeImageUrl,
            contentList = ingredients,
            onErrorAccepted = actionListener::onErrorMessageCleared,
            onAccepted = actionListener::onCompleted
        )
    }
}