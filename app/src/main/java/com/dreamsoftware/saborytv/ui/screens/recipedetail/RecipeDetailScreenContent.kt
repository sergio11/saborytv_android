package com.dreamsoftware.saborytv.ui.screens.recipedetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dreamsoftware.saborytv.ui.screens.recipedetail.components.ChallengeTabs
import com.dreamsoftware.saborytv.ui.screens.recipedetail.components.TrainingEntityDetails
import com.dreamsoftware.fudge.component.FudgeTvRoundedGradientImage
import com.dreamsoftware.fudge.component.FudgeTvScreenContent

@Composable
internal fun RecipeDetailScreenContent(
    state: RecipeDetailUiState,
    actionListener: RecipeDetailScreenActionListener
) {
    FudgeTvScreenContent(onErrorAccepted = actionListener::onErrorMessageCleared) {
        var isChallengeTabsVisible by remember { mutableStateOf(false) }
        Column(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(visible = !isChallengeTabsVisible) {
                Box(contentAlignment = Alignment.BottomStart) {
                    FudgeTvRoundedGradientImage(imageUrl = state.imageUrl)
                    TrainingEntityDetails(
                        state = state,
                        onStartTrainingClicked = actionListener::onRecipeStarted,
                        onMoreInfoClicked = actionListener::onRecipeMoreInfoRequested,
                        onChallengesPlanClicked = { isChallengeTabsVisible = true },
                        onTrainingFavoriteClicked = actionListener::onRecipeFavoriteClicked
                    )
                }
            }
            AnimatedVisibility(visible = isChallengeTabsVisible) {
                ChallengeTabs(
                    state = state,
                    onClickBackChallenge = { isChallengeTabsVisible = false },
                    onClickCard = {}
                )
            }
        }
    }
}