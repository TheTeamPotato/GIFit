package com.theteampotato.gifit.favorites.viewmodel

import androidx.lifecycle.*

import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import com.theteampotato.gifit.domain.mapper.toSearchResult
import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.domain.usecase.GetFavoriteSearchResults
import com.theteampotato.gifit.domain.usecase.RemoveFavoriteSearchResult
import com.theteampotato.gifit.testing.DispatcherProvider

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider? = null,
    private val getFavoriteSearchResults: GetFavoriteSearchResults,
    private val removeFavoriteSearchResult: RemoveFavoriteSearchResult
) : ViewModel() {

    private val mFavoriteSearchResultLiveData = MutableLiveData<List<SearchResult>?>()
    val favoriteSearchResultLiveData = mFavoriteSearchResultLiveData

    lateinit var searchResultEntityListLiveData: LiveData<List<SearchResultEntity>>
    lateinit var favoriteSearchResultObserver: Observer<List<SearchResultEntity>?>

    override fun onCleared() {
        Timber.d("onCleared()")
        searchResultEntityListLiveData.removeObserver(favoriteSearchResultObserver)
        super.onCleared()
    }

    suspend fun retrieveFavoriteSearchResults() {
        searchResultEntityListLiveData = getFavoriteSearchResults()
        favoriteSearchResultObserver = Observer { searchResultEntityList: List<SearchResultEntity>? ->
            Timber.d("Search Result is $searchResultEntityList")
            mFavoriteSearchResultLiveData.value = searchResultEntityList?.map { it.toSearchResult() }?.toList()
        }
        searchResultEntityListLiveData.observeForever(favoriteSearchResultObserver)
    }

    fun removeFavoriteSearchResult(id: Long) {
        val dispatcher = dispatcherProvider?.io ?: EmptyCoroutineContext
        viewModelScope.launch(dispatcher) { removeFavoriteSearchResult.invoke(id) }
    }

}