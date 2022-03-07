package com.theteampotato.gifit.home.viewmodel

import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import com.theteampotato.gifit.domain.mapper.toSearchResult

import com.theteampotato.gifit.domain.mapper.toSearchResultEntity
import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.domain.usecase.AddSearchResultEntry
import com.theteampotato.gifit.domain.usecase.GetSearchResult
import com.theteampotato.gifit.domain.usecase.AddSearchResultToFavorites
import com.theteampotato.gifit.domain.usecase.IsSearchResultExist
import com.theteampotato.gifit.testing.DispatcherProvider

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toList

import javax.inject.Inject

import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider? = null,
    private val addSearchResultEntry: AddSearchResultEntry,
    private val addSearchResultToFavorites: AddSearchResultToFavorites,
    private val getSearchResult: GetSearchResult,
    private val isSearchResultExist: IsSearchResultExist
) : ViewModel() {

    private val mSearchResultLiveData = mutableStateOf<SearchResult?>(null)
    val searchResultState = mSearchResultLiveData

    private var lastID = -1L

    fun addToFavorites() {
        val dispatcher = dispatcherProvider?.io ?: EmptyCoroutineContext

        viewModelScope.launch(dispatcher) {
            addSearchResultToFavorites(lastID)
        }
    }

    fun searchKeyword(text: String) {
        val dispatcher = dispatcherProvider?.io ?: EmptyCoroutineContext

        viewModelScope.launch(dispatcher) {
            /*val x = isSearchResultExist(text).toList().forEach {
                Timber.d("it is $it")
            }
*/
            //isSearchResultExist(text).collect { searchResultEntity ->
            isSearchResultExist(text).collectLatest { searchResultEntity ->
                if (searchResultEntity != null) {
                    mSearchResultLiveData.value = searchResultEntity.toSearchResult()
                    Timber.d("${searchResultEntity.searchText} exists in DB!!")
                } else {
                    getSearchResult(text)?.let { searchResult ->
                        lastID = addSearchResultEntry(searchResult.toSearchResultEntity(text, searchResult.gifURL, false))
                        mSearchResultLiveData.value = searchResult
                        Timber.d("${searchResult.searchText} - remote service call!!")
                    }
                }
            }
        }
    }

}