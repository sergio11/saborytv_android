package com.dreamsoftware.saborytv.ui.screens.ingredients

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import com.dreamsoftware.fudge.component.FudgeTvButton
import com.dreamsoftware.fudge.component.FudgeTvButtonStyleTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvButtonTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvPreviewContent
import com.dreamsoftware.fudge.component.FudgeTvScreenContent
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.saborytv.R

@Composable
fun IngredientsDetailScreenContent(
    uiState: IngredientsDetailUiState,
    actionListener: IIngredientsDetailScreenActionListener
) {
    with(uiState) {
        FudgeTvScreenContent(
            onErrorAccepted = actionListener::onErrorMessageCleared
        ) {
            FudgeTvPreviewContent(
                imageUrl = recipeImageUrl,
                defaultImagePlaceholderRes = R.drawable.main_logo,
                mainTitleRes = R.string.ingredients_main_title_text,
                secondaryTitleRes = R.string.ingredients_main_secondary_text,
                onBuildContent = {
                    IngredientsGrid(ingredients = ingredients)
                },
                onBuildActions = {
                    FudgeTvButton(
                        type = FudgeTvButtonTypeEnum.LARGE,
                        style = FudgeTvButtonStyleTypeEnum.NORMAL,
                        textRes = R.string.ingredients_confirm_button_text,
                        onClick = { }
                    )
                }
            )
        }
    }
}

@Composable
private fun IngredientsGrid(ingredients: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(ingredients.size) { index ->
            val ingredientNumber = "${index + 1}. "
            with(MaterialTheme.colorScheme) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    FudgeTvText(
                        type = FudgeTvTextTypeEnum.BODY_LARGE,
                        textColor = onSurface,
                        titleText = ingredientNumber,
                        textBold = true
                    )
                    FudgeTvText(
                        type = FudgeTvTextTypeEnum.BODY_MEDIUM,
                        textColor = onSurface,
                        titleText = ingredients[index]
                    )
                }
            }
        }
    }
}