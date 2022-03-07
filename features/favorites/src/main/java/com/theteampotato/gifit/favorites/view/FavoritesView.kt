package com.theteampotato.gifit.favorites.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.hilt.navigation.compose.hiltViewModel

import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.favorites.viewmodel.FavoritesViewModel
import com.theteampotato.gifit.ui.view.GIFitCard

import timber.log.Timber

var removedFromFavoritesList = mutableStateListOf<Long>()

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = Unit) {
        viewModel.retrieveFavoriteSearchResults()
    }

    val searchResult: List<SearchResult>? = viewModel.favoriteSearchResultLiveData.observeAsState().value

    Timber.d("searchResult is $searchResult")

    searchResult?.let {
        Column {
            it.forEach {
                GIFitCard(id = it.id ?: -1L, text = it.searchText!!, iconImageVector = Icons.Outlined.Favorite) { id ->
                    if (id != -1L) {
                        viewModel.removeFavoriteSearchResult(id)
                        removedFromFavoritesList.add(id)
                    }
                    else
                        Timber.e("id is -1")
                }
            }
        }
    }

}