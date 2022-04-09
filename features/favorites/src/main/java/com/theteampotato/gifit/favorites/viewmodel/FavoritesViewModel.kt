package com.theteampotato.gifit.favorites.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*

import com.theteampotato.gifit.domain.mapper.toSearchResult
import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.domain.usecase.GetFavoriteSearchResults
import com.theteampotato.gifit.domain.usecase.RemoveSearchResultFromFavorites
import com.theteampotato.gifit.domain.usecase.DeleteSearchResultsFromLocalDb
import com.theteampotato.gifit.testing.DispatcherProvider

import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

import timber.log.Timber

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider? = null,
    private val getFavoriteSearchResults: GetFavoriteSearchResults,
    private val removeSearchResultFromFavorites: RemoveSearchResultFromFavorites,
    private val deleteSearchResultsFromLocalDb: DeleteSearchResultsFromLocalDb
) : ViewModel() {

    private val mFavoriteSearchResultListState = mutableStateOf<List<SearchResult>?>(null)
    val favoriteSearchResultListState = mFavoriteSearchResultListState

    suspend fun retrieveFavoriteSearchResults() {
        getFavoriteSearchResults().collectLatest { searchResultEntityList ->
            Timber.d("Search Result is $searchResultEntityList")
            mFavoriteSearchResultListState.value =
                searchResultEntityList.map { it.toSearchResult() }.toList()
        }
    }

    fun removeFavoriteSearchResult(id: Long) {
        val dispatcher = dispatcherProvider?.io ?: EmptyCoroutineContext
        viewModelScope.launch(dispatcher) { removeSearchResultFromFavorites.invoke(id) }
    }

    fun deleteSearchResultsFromLocalDb() {
        val dispatcher = dispatcherProvider?.io ?: EmptyCoroutineContext
        viewModelScope.launch(dispatcher) { deleteSearchResultsFromLocalDb.invoke() }
    }

}