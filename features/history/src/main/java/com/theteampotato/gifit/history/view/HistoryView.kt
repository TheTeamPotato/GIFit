package com.theteampotato.gifit.history.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.history.viewmodel.HistoryViewModel
import com.theteampotato.gifit.ui.view.*

@Composable
fun HistoryScreen(viewModel : HistoryViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        val currentRoute = remember { mutableStateOf(BottomNavScreens.History.route) }

        GIFitBottomNavBar(
            currentRoute = currentRoute.value,
            navigateTo = {
            currentRoute.value = when (it) {
                BottomNavScreens.Favorites.route -> BottomNavScreens.Favorites.route
                BottomNavScreens.History.route -> BottomNavScreens.History.route
                BottomNavScreens.Search.route -> BottomNavScreens.Search.route
                BottomNavScreens.Trending.route -> BottomNavScreens.Trending.route
                else -> currentRoute.value
            }
        }, items = getScreens())
    }) {
        viewModel.getListOfHistory()
        val list by viewModel.mHistoryListLiveData.observeAsState()

        list?.let { item -> GIFitList(items = item.map { it.searchString }) }
    }
}