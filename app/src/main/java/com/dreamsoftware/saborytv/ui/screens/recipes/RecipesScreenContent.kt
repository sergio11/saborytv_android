package com.dreamsoftware.saborytv.ui.screens.recipes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.domain.model.SortTypeEnum
import com.dreamsoftware.saborytv.ui.theme.surfaceContainerHigh
import com.dreamsoftware.fudge.component.FudgeTvButton
import com.dreamsoftware.fudge.component.FudgeTvButtonStyleTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvButtonTypeEnum
import com.dreamsoftware.fudge.component.FudgeTvCard
import com.dreamsoftware.fudge.component.FudgeTvFocusRequester
import com.dreamsoftware.fudge.component.FudgeTvLoadingState
import com.dreamsoftware.fudge.component.FudgeTvNoContentState
import com.dreamsoftware.fudge.component.FudgeTvTabRow
import com.dreamsoftware.fudge.component.menu.FudgeTvFilterSideMenu
import com.dreamsoftware.fudge.component.menu.FudgeTvOptionsSideMenu
import com.dreamsoftware.fudge.component.menu.FudgeTvSideMenu
import com.dreamsoftware.fudge.utils.conditional

@Composable
internal fun RecipesScreenContent(
    state: RecipesUiState,
    actionListener: RecipesScreenActionListener
) {
    with(actionListener) {
        with(state) {
            FudgeTvSideMenu(
                onDismissSideMenu = ::onDismissFilterSideMenu,
                backgroundContainerColor = surfaceContainerHigh,
                isSideMenuExpended = isFilterExpended
            ) {
                FudgeTvFilterSideMenu(
                    onClearFilters = ::onFilterCleared,
                    filtrationFields = filterItems,
                    titleRes = R.string.filters,
                    clearButtonTextRes = R.string.clear,
                    onFieldClicked = ::onFilterFieldSelected
                )
            }
            FudgeTvSideMenu(
                onDismissSideMenu = ::onDismissFieldFilterSideMenu,
                backgroundContainerColor = surfaceContainerHigh,
                isSideMenuExpended = isFieldFilterSelected
            ) {
                selectedFilter?.run {
                    FudgeTvOptionsSideMenu(
                        onDismissSideMenu = ::onDismissFieldFilterSideMenu,
                        selectedIndex = selectedOption,
                        titleRes = title,
                        items = options,
                        onSelectedItem = ::onSelectedFilterOption
                    )
                }
            }
            FudgeTvSideMenu(
                onDismissSideMenu = ::onDismissSortSideMenu,
                backgroundContainerColor = surfaceContainerHigh,
                isSideMenuExpended = isSortExpended
            ) {
                FudgeTvOptionsSideMenu(
                    onDismissSideMenu = ::onSortCleared,
                    titleRes = R.string.sort_by,
                    items = SortTypeEnum.entries.map { it.value },
                    selectedIndex = selectedSortItem,
                    onSelectedItem = ::onSelectedSortedItem
                )
            }
            RecipesList(
                state = this,
                actionListener = actionListener
            )
        }
    }
}

@Composable
private fun RecipesList(
    state: RecipesUiState,
    actionListener: RecipesScreenActionListener
) {
    FudgeTvFocusRequester(state) { focusRequester ->
        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row(
                    modifier = Modifier.padding(top = 50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Spacer(modifier = Modifier.width(20.dp))
                    FudgeTvTabRow(
                        tabsRes = state.tabsTitle,
                        selectedTabIndex = state.selectedTab,
                        focusTabIndex = state.focusTabIndex,
                        onClick = actionListener::onChangeSelectedTab,
                        onFocus = actionListener::onChangeFocusTab,
                    )
                    Spacer(modifier = Modifier.weight(1F))
                    FudgeTvButton(
                        type = FudgeTvButtonTypeEnum.SMALL,
                        style = FudgeTvButtonStyleTypeEnum.TRANSPARENT,
                        textRes = R.string.filters_button,
                        onClick = actionListener::onFilterClicked
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    FudgeTvButton(
                        modifier = Modifier.width(140.dp),
                        type = FudgeTvButtonTypeEnum.SMALL,
                        style = FudgeTvButtonStyleTypeEnum.TRANSPARENT,
                        text = "${stringResource(id = R.string.sort_by)}: ${SortTypeEnum.entries[state.selectedSortItem].value}",
                        onClick = actionListener::onSortedClicked
                    )
                    Spacer(modifier = Modifier.width(58.dp))
                }
            }

            item {
                if (state.isLoading) {
                    FudgeTvLoadingState(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                    )
                } else if(state.recipes.isEmpty()) {
                    FudgeTvNoContentState(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                        messageRes = R.string.recipes_not_available
                    )
                }
                AnimatedVisibility(
                    visible = !state.isLoading && state.recipes.isNotEmpty(),
                    enter = fadeIn(
                        animationSpec = tween(800)
                    ),
                    exit = fadeOut(
                        animationSpec = tween(800)
                    ),
                ) {
                    LazyHorizontalGrid(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(650.dp),
                        rows = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        contentPadding = PaddingValues(32.dp)
                    ) {
                        itemsIndexed(state.recipes) { idx, recipe ->
                            with(recipe) {
                                FudgeTvCard(
                                    modifier = Modifier.conditional(condition = idx == 0, ifTrue = {
                                        focusRequester(focusRequester)
                                    }),
                                    imageUrl = recipe.imageUrl,
                                    title = title,
                                    timeText = preparationTime.toString(),
                                    typeText = difficulty.value,
                                    onClick = { actionListener.onItemClicked(id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}