package com.dreamsoftware.saborytv.ui.screens.recipedetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dreamsoftware.fudge.component.FudgeTvRoundedGradientImage
import com.dreamsoftware.fudge.component.FudgeTvScreenContent
import com.dreamsoftware.saborytv.ui.screens.recipedetail.components.RecipeEntityDetails

@Composable
internal fun RecipeDetailScreenContent(
    state: RecipeDetailUiState,
    actionListener: RecipeDetailScreenActionListener
) {
    FudgeTvScreenContent(onErrorAccepted = actionListener::onErrorMessageCleared) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(contentAlignment = Alignment.BottomStart) {
                FudgeTvRoundedGradientImage(imageUrl = state.imageUrl)
                RecipeEntityDetails(
                    state = state,
                    onOpenRecipeClicked = actionListener::onRecipeStarted,
                    onMoreInfoClicked = actionListener::onRecipeMoreInfoRequested,
                    onRecipeFavoriteClicked = actionListener::onRecipeFavoriteClicked
                )
            }
        }
    }
}