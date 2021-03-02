package com.theteampotato.gifit.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.domain.usecase.GetSearchResult

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSearchResult: GetSearchResult
) : ViewModel() {

    private val searchResultLiveData = MutableLiveData<SearchResult>()
    val mSearchResultLiveData = searchResultLiveData

    fun searchKeyword(text: String) = viewModelScope.launch {
        searchResultLiveData.value = getSearchResult(text)
    }

}