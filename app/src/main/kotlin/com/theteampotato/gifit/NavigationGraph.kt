package com.theteampotato.gifit

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.theteampotato.gifit.favorites.view.FavoritesScreen
import com.theteampotato.gifit.home.view.SearchScreen
import com.theteampotato.gifit.ui.BottomNavScreen

@Composable
fun NavigationGraph(navController: NavHostController, startDestination: String = BottomNavScreen.SearchNavScreen.route) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(BottomNavScreen.FavoritesNavScreen.route) {
            HomeScaffold(navController = navController, navigateTo = { navigate(navController, it) }) {
                FavoritesScreen(
                    modifier = it,
                    navigateToSearch = { searchQueryArgument ->
                        navController.navigate("${BottomNavScreen.SearchNavScreen.route}/$searchQueryArgument")
                    }
                )
            }
        }
        composable(BottomNavScreen.HistoryNavScreen.route) {
            HomeScaffold(navController = navController, navigateTo = { navigate(navController, it) }) {
                FavoritesScreen(it, navigateToSearch = { navController.navigate(BottomNavScreen.SearchNavScreen.route) })
            }
        }
        composable(BottomNavScreen.SearchNavScreen.route) {
            HomeScaffold(navController = navController, navigateTo = { navigate(navController, it) }) {
                SearchScreen()
            }
        }
        composable(
            route = "${BottomNavScreen.SearchNavScreen.route}/{${BottomNavScreen.SearchNavScreen.arguments?.first()}}",
            arguments = listOf(navArgument("${BottomNavScreen.SearchNavScreen.arguments?.first()}") { type = NavType.StringType })
        ) { backStackEntry ->
            HomeScaffold(navController = navController, navigateTo = { navigate(navController, it) }) {
                SearchScreen(searchQueryArgument = backStackEntry.arguments?.getString(BottomNavScreen.SearchNavScreen.arguments?.first().toString()))
            }
        }
    }
}

private fun navigate(navController: NavController, route: String) {
    navController.navigate(route) {
        launchSingleTop = true
        restoreState = true

        // Pop up backstack to the first destination and save state. This makes going back
        // to the start destination when pressing back in any other bottom tab.
        popUpTo(findStartDestination(navController.graph).id) {
            saveState = true
        }
    }
}

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

/**
 * Copied from similar function in NavigationUI.kt
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-ui/src/main/java/androidx/navigation/ui/NavigationUI.kt
 */
private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph && graph.startDestination != null)
        findStartDestination(graph.startDestination!!)
    else
        graph
}