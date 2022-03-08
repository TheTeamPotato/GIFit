package com.theteampotato.gifit.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import com.theteampotato.gifit.home.viewmodel.SearchViewModel
import com.theteampotato.gifit.ui.view.*

import timber.log.Timber

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val horizontalPadding = 25.dp
    val searchedQuery = rememberSaveable { mutableStateOf("") }
    val searchResult by viewModel.searchResultState

    val isFavoriteState = rememberSaveable() { mutableStateOf(false) }

    LaunchedEffect(key1 = isFavoriteState.value) {
        Timber.d("Composition")

        if (searchedQuery.value.isNotEmpty()) {
            viewModel.searchKeyword(text = searchedQuery.value)
            Timber.d("searchedQuery is not empty")
        }
    }

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
                isFavorite = it.isFavorite,
                onFavoriteClicked = {
                    isFavoriteState.value = !isFavoriteState.value

                    if (isFavoriteState.value)
                        viewModel.addToFavorites()
                    else
                        viewModel.removeFromFavorites()
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewSearchScreen() {
    //SearchScreen()
}