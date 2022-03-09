package com.theteampotato.gifit.history.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.theteampotato.gifit.domain.mapper.toSearchResult
import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.domain.usecase.GetHistoryResults
import com.theteampotato.gifit.domain.usecase.RemoveSearchResultFromHistory
import com.theteampotato.gifit.domain.usecase.RemoveSearchResultsFromLocalDb
import com.theteampotato.gifit.testing.DispatcherProvider

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider? = null,
    private val getHistoryResults: GetHistoryResults,
    private val removeSearchResultFromHistory: RemoveSearchResultFromHistory,
    private val removeSearchResultsFromLocalDb: RemoveSearchResultsFromLocalDb,
) : ViewModel() {

    private val mHistoryResultListState = mutableStateOf<List<SearchResult>?>(null)
    val historyResultListState = mHistoryResultListState

    suspend fun retrieveHistoryResults() {
        getHistoryResults().collectLatest { searchResultEntityList ->
            Timber.d("Search Result is $searchResultEntityList")
            historyResultListState.value =
                searchResultEntityList.map { it.toSearchResult() }.toList()
        }
    }

    fun removeHistorySearchResult(id: Long) {
        val dispatcher = dispatcherProvider?.io ?: EmptyCoroutineContext
        viewModelScope.launch(dispatcher) { removeSearchResultFromHistory.invoke(id) }
    }

    fun removeSearchResultsFromLocalDb() {
        val dispatcher = dispatcherProvider?.io ?: EmptyCoroutineContext
        viewModelScope.launch(dispatcher) { removeSearchResultsFromLocalDb.invoke() }
    }
}