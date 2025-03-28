package com.dreamsoftware.saborytv.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.CarouselState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.dreamsoftware.fudge.component.FudgeTvScreen

@OptIn(ExperimentalTvMaterial3Api::class)
val carouselSaver =
    Saver<CarouselState, Int>(save = { it.activeItemIndex }, restore = { CarouselState(it) })

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onOpenCategoryDetail: (String) -> Unit,
    onOpenRecipeDetail: (String) -> Unit,
    onGoToSubscriptions: () -> Unit
) {
    val carouselState = rememberSaveable(saver = carouselSaver) { CarouselState(0) }
    FudgeTvScreen(
        viewModel = viewModel,
        onInitialUiState = { HomeUiState() },
        onSideEffect = {
            when(it) {
                is HomeSideEffects.OpenRecipesCategory -> onOpenCategoryDetail(it.categoryId)
                is HomeSideEffects.OpenRecipeDetail -> onOpenRecipeDetail(it.id)
                HomeSideEffects.NoActivePremiumSubscription -> onGoToSubscriptions()
            }
        },
        onInit = {
            fetchData()
        }
    ) { uiState ->
        HomeScreenContent(
            state = uiState,
            carouselState = carouselState,
            actionListener = viewModel
        )
    }
}

