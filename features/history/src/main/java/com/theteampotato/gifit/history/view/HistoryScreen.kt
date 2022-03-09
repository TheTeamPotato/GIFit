package com.theteampotato.gifit.history.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.hilt.navigation.compose.hiltViewModel

import com.theteampotato.gifit.history.viewmodel.HistoryViewModel
import com.theteampotato.gifit.ui.view.GIFitCard

import timber.log.Timber

var removedFromHistoryList = mutableStateListOf<Long>()

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    navigateToSearch: (String) -> Unit,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.retrieveHistoryResults()
    }

    val searchResultList by viewModel.historyResultListState

    Timber.d("searchResult is $searchResultList")

    searchResultList?.let {
        LazyColumn(modifier) {
            items(it) { searchResult ->
                GIFitCard(
                    id = searchResult.id ?: -1L,
                    text = searchResult.searchText!!,
                    iconImageVector = Icons.Filled.Delete,
                    colorFilter = ColorFilter.tint(Color.Black),
                    onClicked = { navigateToSearch(searchResult.searchText!!) },
                    onFavoriteClicked = { id ->
                        if (id != -1L) {
                            viewModel.removeHistorySearchResult(id)
                            viewModel.removeSearchResultsFromLocalDb()
                            removedFromHistoryList.add(id)
                        } else
                            Timber.e("id is -1")
                    }
                )
            }
        }
    }
}