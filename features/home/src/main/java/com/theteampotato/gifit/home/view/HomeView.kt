package com.theteampotato.gifit.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import com.theteampotato.gifit.ui.view.*

@Composable
fun HomeScreen() {
    val horizontalPadding = 25.dp

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            val searchedQuery = remember { mutableStateOf("") }

            GIFitSearchBar(
                modifier = Modifier.padding(top = 35.dp, start = horizontalPadding, end = horizontalPadding),
                searchQuery = searchedQuery.value,
                onSearchQueryChanged = {
                    searchedQuery.value = it
                }
            )
        },
        bottomBar = {
            val currentRoute = remember { mutableStateOf(BottomNavScreens.Search.route) }

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
                },
                items = getScreens()
            )
        }) {
        ResultCard(modifier = Modifier.padding(top = 15.dp, start = horizontalPadding, end = horizontalPadding), imageURL = "https://j.gifs.com/yw4AO5.gif")
    }
}