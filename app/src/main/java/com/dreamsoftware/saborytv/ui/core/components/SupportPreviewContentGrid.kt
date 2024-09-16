package com.dreamsoftware.saborytv.ui.core.components

import androidx.annotation.StringRes
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
fun SupportPreviewContentGrid(
    @StringRes mainTitleRes: Int,
    @StringRes secondaryTitleRes: Int,
    @StringRes confirmButtonTextRes: Int,
    imageUrl: String,
    contentList: List<String>,
    onErrorAccepted: () -> Unit,
    onAccepted: () -> Unit
) {
    FudgeTvScreenContent(
        onErrorAccepted = onErrorAccepted
    ) {
        FudgeTvPreviewContent(
            imageUrl = imageUrl,
            defaultImagePlaceholderRes = R.drawable.main_logo,
            mainTitleRes = mainTitleRes,
            secondaryTitleRes = secondaryTitleRes,
            onBuildContent = {
                ContentGrid(contentList = contentList)
            },
            onBuildActions = {
                FudgeTvButton(
                    type = FudgeTvButtonTypeEnum.LARGE,
                    style = FudgeTvButtonStyleTypeEnum.NORMAL,
                    textRes = confirmButtonTextRes,
                    onClick = onAccepted
                )
            }
        )
    }
}

@Composable
private fun ContentGrid(contentList: List<String>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(contentList.size) { index ->
            val contentNumber = "${index + 1}. "
            with(MaterialTheme.colorScheme) {
                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    FudgeTvText(
                        type = FudgeTvTextTypeEnum.BODY_LARGE,
                        textColor = onSurface,
                        titleText = contentNumber,
                        textBold = true
                    )
                    FudgeTvText(
                        type = FudgeTvTextTypeEnum.BODY_MEDIUM,
                        textColor = onSurface,
                        titleText = contentList[index]
                    )
                }
            }
        }
    }
}