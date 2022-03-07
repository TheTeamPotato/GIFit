package com.theteampotato.gifit.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.home.viewmodel.SearchViewModel
import com.theteampotato.gifit.ui.view.*

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val horizontalPadding = 25.dp
    val searchedQuery = remember { mutableStateOf("") }
    val searchResult by viewModel.searchResultState

    Column {
        GIFitSearchBar(
            modifier = Modifier.padding(top = 35.dp, start = horizontalPadding, end = horizontalPadding),
            searchQuery = searchedQuery.value,
            onSearchQueryChanged = { searchedQuery.value = it },
            onSearchQueryEntered = { viewModel.searchKeyword(text = searchedQuery.value) }
        )

        searchResult?.let {
            ResultCard(
                modifier = Modifier.padding(top = 15.dp, start = horizontalPadding, end = horizontalPadding),
                imageURL = it.gifURL,
                translatedText = it.translatedText,
                isFavorite = false,
                onFavoriteClicked = {
                    viewModel.addToFavorites()
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen()
}