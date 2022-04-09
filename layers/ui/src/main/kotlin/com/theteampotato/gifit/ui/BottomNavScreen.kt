package com.theteampotato.gifit.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(val route: String, val arguments: List<Any>? = null, @StringRes val resourceId: Int, val icon: ImageVector) {
    object SearchNavScreen : BottomNavScreen(
        route = "search",
        arguments = listOf("searchQuery"),
        resourceId = R.string.search_route,
        icon = Icons.Filled.Search
    )
    object HistoryNavScreen : BottomNavScreen(route = "history", resourceId = R.string.history_route, icon = Icons.Filled.History)
    object FavoritesNavScreen : BottomNavScreen(route = "favorites", resourceId = R.string.favorites_route, icon = Icons.Filled.Favorite)
}

fun getBottomNavigationScreenList() = listOf(BottomNavScreen.SearchNavScreen, BottomNavScreen.HistoryNavScreen, BottomNavScreen.FavoritesNavScreen)