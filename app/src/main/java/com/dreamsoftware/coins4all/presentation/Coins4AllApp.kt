package com.dreamsoftware.coins4all.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dreamsoftware.coins4all.presentation.detail.CoinDetailsScreen
import com.dreamsoftware.coins4all.presentation.list.CoinListScreen
import com.dreamsoftware.coins4all.presentation.favorites.FavoriteCoinsScreen
import com.dreamsoftware.coins4all.presentation.ui.theme.Coins4AllTheme
import com.dreamsoftware.coins4all.presentation.ui.utils.Screen

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun Coins4AllApp() {

    Coins4AllTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.startDestination.route
        ) {
            composable(Screen.CoinListScreen.route) {
                CoinListScreen(navController)
            }
            composable(Screen.CoinDetailsScreen.route + "/{id}") {
                CoinDetailsScreen(navController)
            }
            composable(Screen.FavoriteCoinsScreen.route) {
                FavoriteCoinsScreen(navController)
            }
        }
    }

}