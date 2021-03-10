package com.theteampotato.gifit.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.domain.usecase.GetSearchResult
import com.theteampotato.gifit.home.DispatcherProvider

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider? = null,
    private val getSearchResult: GetSearchResult
) : ViewModel() {

    private val searchResultLiveData = MutableLiveData<SearchResult>()
    val mSearchResultLiveData = searchResultLiveData

    fun searchKeyword(text: String) {
        val dispatcher = dispatcherProvider?.ui ?: EmptyCoroutineContext

        viewModelScope.launch(dispatcher) {
            searchResultLiveData.value = getSearchResult(text)
        }
    }

}