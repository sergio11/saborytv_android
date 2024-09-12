package com.dreamsoftware.saborytv.ui.navigation

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.dreamsoftware.saborytv.ui.screens.category.CategoryDetailScreenArgs
import com.dreamsoftware.saborytv.ui.screens.chefprofiledetail.ChefProfileDetailScreenArgs
import com.dreamsoftware.saborytv.ui.screens.moreoptions.MoreOptionsScreenArgs
import com.dreamsoftware.saborytv.ui.screens.player.VideoPlayerScreenArgs
import com.dreamsoftware.saborytv.ui.screens.profiles.changesecurepin.ChangeSecurePinScreenArgs
import com.dreamsoftware.saborytv.ui.screens.profiles.delete.DeleteProfileScreenArgs
import com.dreamsoftware.saborytv.ui.screens.profiles.save.SaveProfileScreenArgs
import com.dreamsoftware.saborytv.ui.screens.profiles.secure.SecurePinScreenArgs
import com.dreamsoftware.saborytv.ui.screens.recipedetail.RecipeDetailScreenArgs

sealed class Screen(
    val route: String,
    val name: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {

    data object Splash: Screen(route = "splash", name = "Splash")
    data object Onboarding: Screen(route = "onboarding", name = "Onboarding")
    data object SignIn: Screen(route = "sign_in", name = "SignIn")
    data object SignUp: Screen(route = "sign_up", name = "SignUp")
    data object Dashboard: Screen(route = "dashboard", name = "Dashboard")
    data object Subscription: Screen(route = "subscription", name = "Subscription")
    data object Profiles: Screen(route = "profiles", name = "Profiles")
    data object ProfileSelector: Screen(route = "profile_selector", name = "ProfileSelector")
    data object ProfilesManagement: Screen(route = "profile_management", name = "ProfilesManagement")
    data object AddProfile: Screen(route = "add_profile", name = "AddProfile")
    data object EditProfile: Screen(route = "edit_profile/{id}", name = "EditProfile", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): SaveProfileScreenArgs? = with(args) {
            getString("id")?.let { id ->
                SaveProfileScreenArgs(
                    profileId = id,
                )
            }
        }
    }
    data object ProfileAdvance: Screen(route = "profile_advance/{id}", name = "ProfileAdvance", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): ChangeSecurePinScreenArgs? = with(args) {
            getString("id")?.let { id ->
                ChangeSecurePinScreenArgs(
                    profileId = id,
                )
            }
        }
    }
    data object DeleteProfile: Screen(route = "delete_profile/{id}", name = "DeleteProfile", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): DeleteProfileScreenArgs? = with(args) {
            getString("id")?.let { id ->
                DeleteProfileScreenArgs(
                    profileId = id,
                )
            }
        }
    }
    data object UnlockProfile: Screen(route = "unlock_profile/{id}", name = "UnlockProfile", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): SecurePinScreenArgs? = with(args) {
            getString("id")?.let { id ->
                SecurePinScreenArgs(id)
            }
        }
    }

    data object Home: Screen(route = "home", name = "Home")
    data object Recipes: Screen(route = "recipes", name = "Recipes")
    data object Favorite: Screen(route = "favorite", name = "Favorite")
    data object Settings: Screen(route = "settings", name = "Settings")
    data object VideoPlayer: Screen(route = "video_player/{id}", name = "VideoPlayer", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): VideoPlayerScreenArgs? = with(args) {
            getString("id")?.let { id ->
                VideoPlayerScreenArgs(id = id)
            }
        }
    }
    data object RecipeDetail : Screen(route = "recipe_detail/{id}", name = "RecipeDetail", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): RecipeDetailScreenArgs? = with(args) {
            getString("id")?.let { id ->
                RecipeDetailScreenArgs(id = id)
            }
        }
    }

    data object CategoryDetail : Screen(route = "category_detail/{id}", name = "CategoryDetail", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): CategoryDetailScreenArgs? = with(args) {
            getString("id")?.let { id ->
                CategoryDetailScreenArgs(id = id)
            }
        }
    }

    data object ChefProfileDetail : Screen(route = "chef_profile_detail/{id}", name = "ChefProfileDetail", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): ChefProfileDetailScreenArgs? = with(args) {
            getString("id")?.let { id ->
                ChefProfileDetailScreenArgs(id = id)
            }
        }
    }

    data object MoreOptions : Screen(route = "more_options/{id}", name = "MoreOptions", arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
        }
    )) {
        fun buildRoute(id: String): String =
            route.replace(
                oldValue = "{id}",
                newValue = id
            )

        fun parseArgs(args: Bundle): MoreOptionsScreenArgs? = with(args) {
            getString("id")?.let { id ->
                MoreOptionsScreenArgs(id = id)
            }
        }
    }
}