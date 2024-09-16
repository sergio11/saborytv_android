package com.dreamsoftware.saborytv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dreamsoftware.saborytv.ui.screens.category.CategoryDetailScreen
import com.dreamsoftware.saborytv.ui.screens.favorites.FavoritesScreen
import com.dreamsoftware.saborytv.ui.screens.home.HomeScreen
import com.dreamsoftware.saborytv.ui.screens.chefprofiledetail.ChefProfileDetailScreen
import com.dreamsoftware.saborytv.ui.screens.ingredients.IngredientsDetailScreen
import com.dreamsoftware.saborytv.ui.screens.instructions.InstructionsDetailScreen
import com.dreamsoftware.saborytv.ui.screens.moreoptions.MoreOptionsScreen
import com.dreamsoftware.saborytv.ui.screens.player.VideoPlayerScreen
import com.dreamsoftware.saborytv.ui.screens.settings.SettingsScreen
import com.dreamsoftware.saborytv.ui.screens.subscription.SubscriptionScreen
import com.dreamsoftware.saborytv.ui.screens.recipes.RecipesScreen
import com.dreamsoftware.saborytv.ui.screens.recipedetail.RecipeDetailScreen

@Composable
fun DashboardNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            with(navController) {
                HomeScreen(
                    onOpenCategoryDetail = { id ->
                        navigate(Screen.CategoryDetail.buildRoute(id))
                    },
                    onGoToSubscriptions = {
                        navigate(Screen.Subscription.route)
                    },
                    onOpenRecipeDetail = { id ->
                        navigate(Screen.RecipeDetail.buildRoute(id))
                    }
                )
            }
        }
        composable(Screen.Recipes.route) {
            RecipesScreen(
                onGoToRecipeDetail = { id ->
                    navController.navigate(Screen.RecipeDetail.buildRoute(id))
                }
            )
        }
        composable(Screen.Favorite.route) {
            with(navController) {
                FavoritesScreen(
                    onBackPressed = {
                        popBackStack()
                    },
                    onOpenRecipeDetail = { id ->
                        navigate(Screen.RecipeDetail.buildRoute(id))
                    }
                )
            }
        }

        composable(Screen.CategoryDetail.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.CategoryDetail::parseArgs)?.let { args ->
                with(navController) {
                    CategoryDetailScreen(
                        args = args,
                        onOpenRecipeDetail = { id ->
                            navigate(Screen.RecipeDetail.buildRoute(id))
                        },
                        onBackPressed = {
                            popBackStack()
                        }
                    )
                }
            }
        }

        composable(Screen.VideoPlayer.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.VideoPlayer::parseArgs)?.let { args ->
                with(navController) {
                    VideoPlayerScreen(args = args) {
                        popBackStack()
                    }
                }
            }
        }
        composable(Screen.Settings.route) {
            with(navController) {
                SettingsScreen(
                    onGoToSubscriptions = {
                        navigate(Screen.Subscription.route)
                    },
                    onBackPressed = {
                        popBackStack()
                    }
                )
            }
        }
        composable(Screen.RecipeDetail.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.RecipeDetail::parseArgs)?.let { args ->
                with(navController) {
                    RecipeDetailScreen(
                        args = args,
                        onPlayRecipeProgram = { id ->
                            navigate(Screen.VideoPlayer.buildRoute(id))
                        },
                        onOpenMoreDetails = { id ->
                            navigate(Screen.MoreOptions.buildRoute(id))
                        },
                        onBackPressed = {
                            popBackStack()
                        }
                    )
                }
            }
        }
        composable(Screen.MoreOptions.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.MoreOptions::parseArgs)?.let { args ->
                with(navController) {
                    MoreOptionsScreen(
                        args = args,
                        onPlayRecipeProgram = { id ->
                            navigate(Screen.VideoPlayer.buildRoute(id))
                        },
                        onOpenChefProfileDetail = {
                            navigate(Screen.ChefProfileDetail.buildRoute(it))
                        },
                        onOpenRecipeIngredients = {
                            navigate(Screen.IngredientsDetail.buildRoute(it))
                        },
                        onOpenRecipeInstructions = {
                            navigate(Screen.InstructionsDetail.buildRoute(it))
                        },
                        onBackPressed = {
                            popBackStack()
                        },
                    )
                }
            }
        }

        composable(Screen.ChefProfileDetail.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.ChefProfileDetail::parseArgs)?.let { args ->
                with(navController) {
                    ChefProfileDetailScreen(
                        args = args,
                        onBackPressed = {
                            popBackStack()
                        },
                    )
                }
            }
        }
        composable(Screen.Subscription.route) {
            with(navController) {
                SubscriptionScreen(
                    onBackPressed = {
                        popBackStack()
                    }
                )
            }
        }

        composable(Screen.IngredientsDetail.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.IngredientsDetail::parseArgs)?.let { args ->
                with(navController) {
                    IngredientsDetailScreen(
                        args = args,
                        onBackPressed = {
                            popBackStack()
                        },
                    )
                }
            }
        }

        composable(Screen.InstructionsDetail.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.let(Screen.InstructionsDetail::parseArgs)?.let { args ->
                with(navController) {
                    InstructionsDetailScreen(
                        args = args,
                        onBackPressed = {
                            popBackStack()
                        },
                    )
                }
            }
        }
    }
}