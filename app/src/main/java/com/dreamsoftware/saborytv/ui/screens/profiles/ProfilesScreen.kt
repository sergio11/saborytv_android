package com.dreamsoftware.saborytv.ui.screens.profiles

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.dreamsoftware.saborytv.ui.navigation.ProfilesNavigation

@Composable
fun ProfilesScreen(
    onGoToHome: () -> Unit
) {
    val navController = rememberNavController()
    ProfilesNavigation(navController = navController, onGoToHome = onGoToHome)
}