package com.dreamsoftware.coins4all.presentation.ui.utils

sealed class Screen(val route: String) {
    object CoinListScreen : Screen(route = "coin_list")
    object CoinDetailsScreen : Screen(route = "coin_details")
    object FavoriteCoinsScreen : Screen(route = "favorite_coins")

    companion object {

        val startDestination: Screen
            get() = CoinListScreen

    }

}
