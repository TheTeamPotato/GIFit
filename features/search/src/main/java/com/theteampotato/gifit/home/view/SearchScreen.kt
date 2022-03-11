package com.theteampotato.gifit.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

import com.theteampotato.gifit.home.viewmodel.SearchViewModel
import com.theteampotato.gifit.ui.view.*

import timber.log.Timber

@Composable
fun SearchScreen(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current, searchQueryArgument: String? = null, viewModel: SearchViewModel = hiltViewModel()) {
    val horizontalPadding = 25.dp
    val searchedQuery = rememberSaveable { mutableStateOf("") }
    val finalSearchedQuery = rememberSaveable { mutableStateOf("") }

    val isFavoriteState = rememberSaveable() { mutableStateOf(false) }

    val searchResult = viewModel.searchKeyword(text = finalSearchedQuery.value.ifBlank { null })?.collectAsState(null)

    DisposableEffect(lifecycleOwner) {
        Timber.d("DisposableEffect")

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY)
                viewModel.releaseResources()
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            Timber.d("onDispose")
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(key1 = searchQueryArgument) {
        Timber.d("Composition")

        searchQueryArgument?.let {
            Timber.d("searchQueryArgument is not null")
            finalSearchedQuery.value = it
        }
    }

    Column {
        GIFitSearchBar(
            modifier = Modifier.padding(top = 35.dp, start = horizontalPadding, end = horizontalPadding),
            searchQuery = searchedQuery.value,
            onSearchQueryChanged = { searchedQuery.value = it },
            onSearchQueryEntered = { finalSearchedQuery.value = searchedQuery.value }
        )

        searchResult?.value?.let {
            Timber.d("searchResult.value?.let is $it")

            ResultCard(
                modifier = Modifier.padding(top = 15.dp, start = horizontalPadding, end = horizontalPadding),
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
}

@Preview
@Composable
fun PreviewSearchScreen() {
    //SearchScreen()
}