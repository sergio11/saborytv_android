package com.dreamsoftware.saborytv.ui.screens.moreoptions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.fudge.component.FudgeTvBackRowSchema
import com.dreamsoftware.fudge.component.FudgeTvCardDetails
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvMoreOptionsButton
import com.dreamsoftware.fudge.component.FudgeTvScreenContent
import com.dreamsoftware.saborytv.ui.theme.surfaceVariant
import com.dreamsoftware.saborytv.ui.utils.formatInfo

@Composable
internal fun MoreOptionsScreenContent(
    state: MoreOptionsUiState,
    actionListener: MoreOptionsScreenActionListener
) {
    with(state) {
        FudgeTvScreenContent(
            onErrorAccepted = actionListener::onErrorMessageCleared
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                ConstraintLayout {
                    val (
                        recipeDetails,
                        backRowSchema,
                        startButton,
                        favoritesButton,
                        viewChefProfileButton,
                        viewIngredientsButton,
                        viewInstructionsButton,
                        shareButton
                    ) = createRefs()

                    FudgeTvCardDetails(
                        modifier = Modifier.width(350.dp).constrainAs(recipeDetails) {},
                        title = recipe?.title.orEmpty(),
                        subtitle = recipe.formatInfo(LocalContext.current, fullDetails = true),
                        description = recipe?.description.orEmpty(),
                        imageUrl = recipe?.imageUrl.orEmpty()
                    )
                    FudgeTvBackRowSchema(
                        modifier = Modifier.constrainAs(backRowSchema) {
                            top.linkTo(recipeDetails.bottom, margin = 50.dp)
                        },
                        onClickBack = actionListener::onBackPressed
                    )
                    FudgeTvFocusRequester { focusRequester ->
                        FudgeTvMoreOptionsButton(
                            modifier = Modifier
                                .focusRequester(focusRequester)
                                .constrainAs(startButton) {
                                    top.linkTo(recipeDetails.top)
                                    start.linkTo(recipeDetails.end, margin = 140.dp)
                                },
                            textRes = R.string.play_recipe_program,
                            icon = R.drawable.ic_rounded_play,
                            containerColor = surfaceVariant,
                            onClick = actionListener::onRecipeOpened
                        )
                    }

                    FudgeTvMoreOptionsButton(
                        modifier = Modifier.constrainAs(favoritesButton) {
                            top.linkTo(startButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = if (isFavorite) {
                            R.string.remove_from_favorites
                        } else {
                            R.string.add_to_favorites
                        },
                        icon = if (isFavorite) {
                            R.drawable.ic_favorite
                        } else {
                            R.drawable.ic_outline_favorite
                        },
                        containerColor = surfaceVariant,
                        onClick = actionListener::onFavouriteClicked
                    )
                    FudgeTvMoreOptionsButton(
                        modifier = Modifier.constrainAs(viewIngredientsButton) {
                            top.linkTo(favoritesButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = R.string.check_recipe_ingredients_button_text,
                        icon = R.drawable.ic_ingredients,
                        containerColor = surfaceVariant,
                        onClick = actionListener::onOpenRecipeIngredients
                    )
                    FudgeTvMoreOptionsButton(
                        modifier = Modifier.constrainAs(viewInstructionsButton) {
                            top.linkTo(viewIngredientsButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = R.string.check_recipe_instructions_button_text,
                        icon = R.drawable.ic_instructions,
                        containerColor = surfaceVariant,
                        onClick = actionListener::onOpenRecipeInstructions
                    )
                    FudgeTvMoreOptionsButton(
                        modifier = Modifier.constrainAs(viewChefProfileButton) {
                            top.linkTo(viewInstructionsButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = R.string.view_chef_profile_button_text,
                        icon = R.drawable.ic_chef_profile,
                        containerColor = surfaceVariant,
                        onClick = actionListener::onOpenChefProfileDetail
                    )
                    FudgeTvMoreOptionsButton(
                        modifier = Modifier.constrainAs(shareButton) {
                            top.linkTo(viewChefProfileButton.bottom, margin = 12.dp)
                            start.linkTo(startButton.start)
                        },
                        textRes = R.string.share_button_text,
                        containerColor = surfaceVariant,
                        icon = R.drawable.ic_share
                    )
                }
            }
        }
    }
}