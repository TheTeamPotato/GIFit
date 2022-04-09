package com.theteampotato.gifit.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.theteampotato.gifit.domain.mapper.toSearchResult
import com.theteampotato.gifit.domain.mapper.toSearchResultEntity
import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.domain.usecase.*
import com.theteampotato.gifit.testing.DispatcherProvider

import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import timber.log.Timber

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider? = null,
    private val addSearchResultEntry: AddSearchResultEntry,
    private val addSearchResultToFavorites: AddSearchResultToFavorites,
    private val getSearchResult: GetSearchResult,
    private val isSearchResultExist: IsSearchResultExist,
    private val removeSearchResultFromFavorites: RemoveSearchResultFromFavorites,
    private val readText: ReadText,
    private val translateText: TranslateText
) : ViewModel() {

    private var currentText: String? = null
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

    fun searchKeyword(text: String?): Flow<SearchResult?>? {
        text?.let {
            return@searchKeyword isSearchResultExist(text)
                .distinctUntilChanged()
                .map { searchResultEntity ->
                    val outputSearchResult: SearchResult?

                    if (searchResultEntity != null) {
                        currentSearchResultID = searchResultEntity.id
                        outputSearchResult = searchResultEntity.toSearchResult()
                        Timber.d("${searchResultEntity.searchText} exists in DB!!")
                    } else {
                        outputSearchResult = searchGifOnService(text)
                    }

                    currentText = outputSearchResult?.translatedText

                    return@map outputSearchResult
                }
        }
        return null
    }

    fun readKeyword() = currentText?.let { readText(it) }

    fun releaseResources() {
        Timber.d("releaseResources()")
        readText.releaseResource()
        translateText.releaseResource()
    }

    private suspend fun searchGifOnService(text: String) = withContext(Dispatchers.IO) {
        translateText(text)?.let {
            val searchResult = getSearchResult(it)
            currentSearchResultID = addSearchResultEntry(searchResult.toSearchResultEntity(text, searchResult.gifURL ?: "", false))
            Timber.d("${searchResult.searchText} - remote service call!!")
            return@let searchResult
        }
    }

}