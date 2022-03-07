package com.theteampotato.gifit.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object SearchNavScreen : BottomNavScreen("Search", R.string.search_route, Icons.Filled.Search)
    object HistoryNavScreen : BottomNavScreen("History", R.string.history_route, Icons.Filled.History)
    object FavoritesNavScreen : BottomNavScreen("Favorites", R.string.favorites_route, Icons.Filled.Favorite)
}

fun getBottomNavigationScreenList() = listOf(BottomNavScreen.SearchNavScreen, BottomNavScreen.HistoryNavScreen, BottomNavScreen.FavoritesNavScreen)