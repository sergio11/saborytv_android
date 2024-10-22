package com.dreamsoftware.saborytv.ui.screens.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.tv.material3.Carousel
import androidx.tv.material3.CarouselDefaults
import androidx.tv.material3.CarouselState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ShapeDefaults
import coil.compose.AsyncImage
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.ui.theme.shadowCarouselColor
import com.dreamsoftware.fudge.component.FudgeTvButton
import com.dreamsoftware.fudge.component.FudgeTvButtonStyleTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvButtonTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.fudge.utils.conditional
import com.dreamsoftware.fudge.utils.shadowBox
import com.dreamsoftware.saborytv.domain.model.RecipeBO

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
internal fun FeaturedRecipes(
    recipes: List<RecipeBO>,
    padding: PaddingValues,
    carouselState: CarouselState,
    onOpenRecipeDetail: (RecipeBO) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isCarouselFocused by remember { mutableStateOf(false) }
    Carousel(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
            .conditional(isCarouselFocused, ifTrue = {
                shadowBox(
                    color = shadowCarouselColor,
                    blurRadius = 40.dp,
                    offset = DpOffset(0.dp, 8.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                )
            })
            .border(
                width = 3.dp,
                color = MaterialTheme.colorScheme.border.copy(alpha = if (isCarouselFocused) 1f else 0f),
                shape = MaterialTheme.shapes.extraLarge,
            )
            .clip(MaterialTheme.shapes.extraLarge)
            .onFocusChanged {
                isCarouselFocused = it.hasFocus
            },
        itemCount = recipes.size,
        carouselState = carouselState,
        carouselIndicator = {
            CarouselIndicator(
                itemCount = recipes.size,
                activeItemIndex = carouselState.activeItemIndex
            )
        },
        contentTransformStartToEnd = fadeIn(tween(durationMillis = 1000)).togetherWith(
            fadeOut(tween(durationMillis = 1000))
        ),
        contentTransformEndToStart = fadeIn(tween(durationMillis = 1000)).togetherWith(
            fadeOut(tween(durationMillis = 1000))
        )
    ) { index ->
        Box(modifier = Modifier.fillMaxSize()) {
            val recipe = recipes[index]
            CarouselItemBackground(
                modifier = Modifier.fillMaxSize(),
                recipe = recipe
            )
            CarouselItemForeground(
                recipe = recipe,
                isCarouselFocused = isCarouselFocused,
                onOpenRecipeDetail = { onOpenRecipeDetail(recipe) },
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun BoxScope.CarouselIndicator(
    modifier: Modifier = Modifier,
    itemCount: Int,
    activeItemIndex: Int,
) {
    Box(modifier = modifier
        .padding(32.dp)
        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
        .graphicsLayer {
            clip = true
            shape = ShapeDefaults.ExtraSmall
        }
        .align(Alignment.BottomEnd)) {
        CarouselDefaults.IndicatorRow(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            itemCount = itemCount,
            activeItemIndex = activeItemIndex,
        )
    }
}

@Composable
private fun CarouselItemForeground(
    recipe: RecipeBO,
    onOpenRecipeDetail: () -> Unit,
    modifier: Modifier = Modifier,
    isCarouselFocused: Boolean = false
) {
    with(MaterialTheme.colorScheme) {
        Column(
            modifier = modifier
                .padding(start = 34.dp, bottom = 32.dp)
                .width(360.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            with(recipe) {
                FudgeTvText(
                    type = FudgeTvTextTypeEnum.LABEL_MEDIUM,
                    titleText = chefProfileName,
                    singleLine = true,
                    textColor = onSurfaceVariant
                )
                FudgeTvText(
                    modifier = Modifier.padding(top = 4.dp),
                    type = FudgeTvTextTypeEnum.HEADLINE_SMALL,
                    titleText = title,
                    singleLine = true,
                    textColor = onSurface
                )
                FudgeTvText(
                    modifier = Modifier.padding(top = 12.dp, bottom = 28.dp),
                    type = FudgeTvTextTypeEnum.BODY_SMALL,
                    titleText = description,
                    singleLine = true,
                    textColor = onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            AnimatedVisibility(visible = isCarouselFocused) {
                FudgeTvButton(
                    type = FudgeTvButtonTypeEnum.LARGE,
                    style = FudgeTvButtonStyleTypeEnum.NORMAL,
                    textRes = R.string.play_recipe_program,
                    onClick = onOpenRecipeDetail
                )
            }
        }
    }
}

@Composable
private fun CarouselItemBackground(recipe: RecipeBO, modifier: Modifier = Modifier) {
    with(MaterialTheme.colorScheme) {
        var sizeCard by remember { mutableStateOf(Size.Zero) }
        AsyncImage(model = recipe.imageUrl,
            contentDescription = stringResource(id = R.string.image, recipe.title),
            modifier = modifier
                .fillMaxSize()
                .aspectRatio(21F / 9F)
                .onGloballyPositioned { coordinates ->
                    sizeCard = coordinates.size.toSize()
                }
                .drawWithContent {
                    drawContent()
                    drawRect(
                        Brush.radialGradient(
                            colors = listOf(
                                primary.copy(alpha = 0.0f),
                                primary.copy(alpha = 0.12f),
                            ),
                            center = Offset(sizeCard.width, -(sizeCard.width * .35F)),
                            radius = sizeCard.width * .75f,
                        )
                    )
                }
                .drawWithContent {
                    drawContent()
                    drawRect(
                        Brush.radialGradient(
                            colors = listOf(
                                surface.copy(alpha = 0.1f),
                                surface.copy(alpha = 0.95f),
                            ),
                            center = Offset(sizeCard.width, -(sizeCard.width * .35F)),
                            radius = sizeCard.width * .75f,
                        )
                    )
                },
            contentScale = ContentScale.Crop
        )
    }
}