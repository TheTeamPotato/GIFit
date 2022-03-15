package com.theteampotato.gifit.home.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.theteampotato.gifit.home.R
import com.theteampotato.gifit.home.viewmodel.SearchViewModel
import com.theteampotato.gifit.ui.view.GIFitLoader
import com.theteampotato.gifit.ui.view.GIFitSearchBar
import com.theteampotato.gifit.ui.view.ResultCard
import timber.log.Timber

@Composable
fun SearchScreen(searchTextArgument: String? = null, viewModel: SearchViewModel = hiltViewModel()) {
    val horizontalPadding = 25.dp
    val searchBarText = rememberSaveable { mutableStateOf(searchTextArgument ?: "") }
    val searchText = rememberSaveable { mutableStateOf("") }
    val isSearched = remember { mutableStateOf(false) }

    val isFavoriteState = rememberSaveable() { mutableStateOf(false) }

    val searchResult =
        viewModel.searchKeyword(text = searchText.value.ifBlank { null })?.collectAsState(null)

    LaunchedEffect(key1 = searchTextArgument) {
        Timber.d("Composition")

        searchTextArgument?.let {
            Timber.d("searchQueryArgument is not null")
            searchText.value = it
        }
    }

    Column {
        GIFitSearchBar(
            modifier = Modifier.padding(
                top = 35.dp,
                start = horizontalPadding,
                end = horizontalPadding
            ),
            text = searchBarText.value,
            onSearchQueryChanged = { searchBarText.value = it },
            onSearchQueryEntered = {
                searchText.value = searchBarText.value
                isSearched.value = true
            },
            placeholderText = stringResource(id = R.string.search) + "..."
        )

        searchResult?.value?.let {
            Timber.d("searchResult.value?.let is $it")

            isSearched.value = false

            ResultCard(
                modifier = Modifier.padding(
                    top = 15.dp,
                    start = horizontalPadding,
                    end = horizontalPadding
                ),
                imageURL = it.gifURL ?: "",
                translatedText = it.translatedText,
                isFavorite = it.isFavorite,
                onFavoriteClicked = {
                    isFavoriteState.value = !isFavoriteState.value

                    if (isFavoriteState.value)
                        viewModel.addToFavorites()
                    else
                        viewModel.removeFromFavorites()
                },
                onListenClicked = {
                    viewModel.readKeyword()
                }
            )
        }
    }

    if (isSearched.value) GIFitLoader()
}

@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen()
}