package com.theteampotato.gifit.favorites.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

import com.theteampotato.gifit.favorites.viewmodel.FavoritesViewModel
import com.theteampotato.gifit.ui.view.GIFitCard

import timber.log.Timber

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier, navigateToSearch: (String) -> Unit, viewModel: FavoritesViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = Unit) {
        viewModel.retrieveFavoriteSearchResults()
    }

    val searchResultList by viewModel.favoriteSearchResultListState

    Timber.d("searchResult is $searchResultList")

    searchResultList?.let {
        LazyColumn(modifier) {
            items(it) { searchResult ->
                GIFitCard(
                    id = searchResult.id ?: -1L,
                    text = searchResult.searchText!!,
                    iconImageVector = Icons.Outlined.Favorite,
                    onClicked = { navigateToSearch(searchResult.searchText!!) },
                    onFavoriteClicked = { id ->
                        if (id != -1L)
                            viewModel.removeFavoriteSearchResult(id)
                        else
                            Timber.e("id is -1")
                    }
                )
            }
        }
    }

}