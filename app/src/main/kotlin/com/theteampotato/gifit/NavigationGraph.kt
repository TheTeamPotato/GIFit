package com.theteampotato.gifit

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.theteampotato.gifit.favorites.view.FavoritesScreen
import com.theteampotato.gifit.favorites.viewmodel.FavoritesViewModel
import com.theteampotato.gifit.history.view.HistoryScreen
import com.theteampotato.gifit.history.viewmodel.HistoryViewModel
import com.theteampotato.gifit.home.view.SearchScreen
import com.theteampotato.gifit.home.viewmodel.SearchViewModel
import com.theteampotato.gifit.ui.BottomNavScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = "${BottomNavScreen.SearchNavScreen.route}?searchQuery={${BottomNavScreen.SearchNavScreen.arguments?.first()}}",
    searchViewModel: SearchViewModel,
    historyViewModel: HistoryViewModel,
    favoritesViewModel: FavoritesViewModel,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(BottomNavScreen.FavoritesNavScreen.route) {
            HomeScaffold(
                navController = navController,
                navigateTo = { navController.navigateSingleTask(it) }) {
                FavoritesScreen(
                    modifier = it,
                    navigateToSearch = { searchQueryArgument ->
                        navController.navigate("${BottomNavScreen.SearchNavScreen.route}?searchQuery=$searchQueryArgument")
                    },
                    viewModel = favoritesViewModel
                )
            }
        }
        composable(BottomNavScreen.HistoryNavScreen.route) {
            HomeScaffold(
                navController = navController,
                navigateTo = { navController.navigateSingleTask(it) }) {
                HistoryScreen(
                    modifier = it,
                    navigateToSearch = { searchQueryArgument ->
                        navController.navigate("${BottomNavScreen.SearchNavScreen.route}?searchQuery=$searchQueryArgument")
                    },
                    viewModel = historyViewModel
                )
            }
        }
        composable(
            route = "${BottomNavScreen.SearchNavScreen.route}?searchQuery={${BottomNavScreen.SearchNavScreen.arguments?.first()}}",
            arguments = listOf(navArgument("${BottomNavScreen.SearchNavScreen.arguments?.first()}") {
                type = NavType.StringType
                nullable = true
            })
        ) { backStackEntry ->
            HomeScaffold(
                navController = navController,
                navigateTo = { navController.navigateSingleTask(it) }) {
                SearchScreen(
                    searchTextArgument = backStackEntry.arguments?.getString(
                        BottomNavScreen.SearchNavScreen.arguments?.first().toString()
                    ),
                    viewModel = searchViewModel
                )
            }
        }
    }
}

fun NavController.navigateSingleTask(route: String) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true

        // Pop up backstack to the first destination and save state. This makes going back
        // to the start destination when pressing back in any other bottom tab.
        popUpTo(findStartDestination(graph).id) {
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