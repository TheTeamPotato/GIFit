package com.theteampotato.gifit.home.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.theteampotato.gifit.domain.mapper.toSearchResult
import com.theteampotato.gifit.domain.mapper.toSearchResultEntity
import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.domain.usecase.*
import com.theteampotato.gifit.testing.DispatcherProvider

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

import timber.log.Timber

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider? = null,
    private val addSearchResultEntry: AddSearchResultEntry,
    private val addSearchResultToFavorites: AddSearchResultToFavorites,
    private val getSearchResult: GetSearchResult,
    private val isSearchResultExist: IsSearchResultExist,
    private val removeSearchResultFromFavorites: RemoveSearchResultFromFavorites
) : ViewModel() {

    private val mSearchResultState = mutableStateOf<SearchResult?>(null)
    val searchResultState = mSearchResultState

    private var currentSearchResultID = -1L

    fun addToFavorites() {
        Timber.d("addFavorites(lastID = $currentSearchResultID)")

        val dispatcher = dispatcherProvider?.io ?: EmptyCoroutineContext

        viewModelScope.launch(dispatcher) {
            addSearchResultToFavorites(currentSearchResultID)
        }
    }

    fun removeFromFavorites() {
        Timber.d("removeFromFavorites(lastID = $currentSearchResultID)")

        val dispatcher = dispatcherProvider?.io ?: EmptyCoroutineContext

        viewModelScope.launch(dispatcher) {
            removeSearchResultFromFavorites.invoke(currentSearchResultID)
        }
    }

    fun searchKeyword(text: String) {
        val dispatcher = dispatcherProvider?.io ?: EmptyCoroutineContext

        viewModelScope.launch(dispatcher) {
            isSearchResultExist(text).distinctUntilChanged().collectLatest { searchResultEntity ->
                if (searchResultEntity != null) {
                    currentSearchResultID = searchResultEntity.id
                    mSearchResultState.value = searchResultEntity.toSearchResult()
                    Timber.d("${searchResultEntity.searchText} exists in DB!!")
                } else {
                    getSearchResult(text)?.let { searchResult ->
                        currentSearchResultID = addSearchResultEntry(searchResult.toSearchResultEntity(text, searchResult.gifURL, false))
                        mSearchResultState.value = searchResult
                        Timber.d("${searchResult.searchText} - remote service call!!")
                    }
                }
            }
        }
    }

}