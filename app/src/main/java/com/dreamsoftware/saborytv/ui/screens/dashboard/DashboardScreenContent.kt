package com.dreamsoftware.saborytv.ui.screens.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.dreamsoftware.saborytv.R
import com.dreamsoftware.saborytv.ui.navigation.DashboardNavHost
import com.dreamsoftware.saborytv.ui.navigation.Screen
import com.dreamsoftware.fudge.component.FudgeTvNavigationDrawer

@Composable
internal fun DashboardScreenContent(
    uiState: DashboardUiState,
    actionListener: DashboardActionListener,
    navController: NavHostController,
    currentDestination: NavDestination?,
) {
    with(uiState) {
        FudgeTvNavigationDrawer(
            modifier = Modifier,
            mainLogoInverseRes = R.drawable.main_logo_background,
            hiddenDrawerRoutes = listOf(Screen.VideoPlayer.route),
            onItemClicked = actionListener::onMenuItemSelected,
            items = items,
            currentDestination = currentDestination,
        ) {
            DashboardNavHost(navController)
        }
    }
}