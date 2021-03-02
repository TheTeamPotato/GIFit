package com.theteampotato.gifit.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.home.viewmodel.HomeViewModel
import com.theteampotato.gifit.ui.view.*

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val horizontalPadding = 25.dp

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            val searchedQuery = remember { mutableStateOf("") }

            GIFitSearchBar(
                modifier = Modifier.padding(top = 35.dp, start = horizontalPadding, end = horizontalPadding),
                searchQuery = searchedQuery.value,
                onSearchQueryChanged = { searchedQuery.value = it },
                onSearchQueryEntered = { viewModel.searchKeyword(text = searchedQuery.value) }
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

        val searchResult: SearchResult? by viewModel.mSearchResultLiveData.observeAsState()

        searchResult?.let {
            ResultCard(
                modifier = Modifier.padding(top = 15.dp, start = horizontalPadding, end = horizontalPadding),
                imageURL = it.giphyResult.data.first().images.original.url,
                translatedText = it.translatedText
            )
        }
    }
}