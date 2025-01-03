package com.dreamsoftware.saborytv.ui.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.fudge.component.FudgeTvCard
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvLoadingState
import com.dreamsoftware.fudge.component.FudgeTvNoContentState
import com.dreamsoftware.fudge.component.FudgeTvScreenContent
import com.dreamsoftware.fudge.component.FudgeTvText
import com.dreamsoftware.fudge.component.FudgeTvTextTypeEnum
import com.dreamsoftware.fudge.utils.conditional
import com.dreamsoftware.saborytv.ui.utils.formatInfo

@Composable
internal fun CategoryDetailScreenContent(
    uiState: CategoryDetailUiState,
    actionListener: CategoryDetailActionListener
) {
    with(uiState) {
        FudgeTvScreenContent(onErrorAccepted = actionListener::onErrorMessageCleared) {
            if (isLoading) {
                FudgeTvLoadingState(modifier = Modifier.fillMaxSize())
            } else if(category != null && recipes.isEmpty()) {
                FudgeTvNoContentState(
                    modifier = Modifier.fillMaxSize(),
                    messageRes = R.string.category_detail_no_recipes_available
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    FudgeTvText(
                        modifier = Modifier.padding(bottom = 8.dp, top = 56.dp, start = 32.dp, end = 32.dp),
                        type = FudgeTvTextTypeEnum.HEADLINE_MEDIUM,
                        titleRes = R.string.category_detail_recipes_title_default.takeUnless { category != null },
                        titleText = category?.let {
                            stringResource(id = R.string.category_detail_recipes_title, it.title)
                        },
                        textBold = true
                    )
                    FudgeTvFocusRequester { focusRequester ->
                        LazyHorizontalGrid(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp),
                            contentPadding = PaddingValues(horizontal = 46.dp),
                            rows = GridCells.Fixed(2),
                            horizontalArrangement = Arrangement.spacedBy(24.dp),
                            verticalArrangement = Arrangement.spacedBy(24.dp)
                        ) {
                            itemsIndexed(items = recipes, key = { _, item -> item.id }) { idx, item ->
                                with(item) {
                                    FudgeTvCard(modifier = Modifier
                                        .conditional(condition = idx == 0, ifTrue = {
                                            focusRequester(focusRequester)
                                        }),
                                        onClick = {
                                            actionListener.onRecipeOpened(item)
                                        },
                                        imageUrl = imageUrl,
                                        title = title,
                                        subtitle = formatInfo(LocalContext.current)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}