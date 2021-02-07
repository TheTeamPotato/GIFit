package com.theteampotato.gifit.ui.view

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.theteampotato.gifit.ui.R

sealed class BottomNavScreens(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Search : BottomNavScreens("Search", R.string.search_route, Icons.Filled.Search)
    object History : BottomNavScreens("History", R.string.history_route, Icons.Filled.History)
    object Favorites : BottomNavScreens("Favorites", R.string.favorites_route, Icons.Filled.Favorite)
    object Trending : BottomNavScreens("Trending", R.string.trending_route, Icons.Filled.TrendingUp)
}

fun getScreens() : List<BottomNavScreens> {
    return listOf(BottomNavScreens.Search, BottomNavScreens.History, BottomNavScreens.Favorites, BottomNavScreens.Trending)
}