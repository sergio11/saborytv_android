package com.dreamsoftware.saborytv.ui.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.MaterialTheme
import coil.compose.AsyncImage
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.domain.model.CategoryBO
import com.dreamsoftware.saborytv.ui.theme.FitFlexTVTheme
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum

@Composable
internal fun Categories(
    categories: List<CategoryBO>,
    onClick: (id: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        FudgeTvText(
            modifier = Modifier.padding(start = 32.dp),
            type = FudgeTvTextTypeEnum.HEADLINE_MEDIUM,
            titleRes = R.string.categories,
            textColor = MaterialTheme.colorScheme.onSurface,
            textBold = true
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = 32.dp)
        ) {
            items(categories) { category ->
                CategoryItem(
                    modifier = modifier,
                    categoryBO = category,
                    onClick = { onClick(category.id) }
                )
            }
        }
    }
}

private const val GRADIENT_START = 0.6f
private const val GRADIENT_END = 1.0f

@Composable
private fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryBO: CategoryBO,
    onClick: () -> Unit
) {
    val gradiantColors = arrayOf(
        GRADIENT_START to MaterialTheme.colorScheme.surfaceVariant,
        GRADIENT_END to Color.Transparent
    )
    with(MaterialTheme.colorScheme) {
        with(categoryBO) {
            Card(
                colors = CardDefaults.colors(Color.Transparent),
                border = CardDefaults.border(
                    border = Border(BorderStroke(width = 2.dp, color = onSurface)),
                    focusedBorder = Border(BorderStroke(width = 3.dp, color = border))
                ),
                onClick = onClick,
            ) {
                Box(
                    modifier = Modifier.clip(MaterialTheme.shapes.small),
                    contentAlignment = Alignment.CenterStart
                ) {
                    AsyncImage(
                        modifier = modifier
                            .size(280.dp, 80.dp)
                            .drawWithContent {
                                drawContent()
                                drawRect(Brush.horizontalGradient(colorStops = gradiantColors))
                            },
                        model = imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        FudgeTvText(
                            modifier = Modifier.width(180.dp),
                            titleText = title,
                            type = FudgeTvTextTypeEnum.BODY_LARGE,
                            textColor = onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        FudgeTvText(
                            modifier = Modifier.width(200.dp),
                            titleText = description,
                            type = FudgeTvTextTypeEnum.BODY_SMALL,
                            textColor = onSurface,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CategoriesPreview() {
    FitFlexTVTheme {
        Categories(
            categories = listOf(
                CategoryBO(
                    id = "1",
                    imageUrl = "",
                    title = "Test 1",
                    description = "Test 1 - Description"
                ),
                CategoryBO(
                    id = "2",
                    imageUrl = "",
                    title = "Test 2",
                    description = "Test 2 - Description"
                ),
            ),
            onClick = {}
        )
    }
}
