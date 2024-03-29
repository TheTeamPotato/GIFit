package com.theteampotato.gifit.history.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.theteampotato.gifit.domain.mapper.toSearchResult
import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.domain.usecase.DeleteSearchResultsFromLocalDb
import com.theteampotato.gifit.domain.usecase.GetHistoryResults
import com.theteampotato.gifit.domain.usecase.RemoveSearchResultFromHistory
import com.theteampotato.gifit.testing.DispatcherProvider

import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

import timber.log.Timber

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider? = null,
    private val getHistoryResults: GetHistoryResults,
    private val removeSearchResultFromHistory: RemoveSearchResultFromHistory,
    private val deleteSearchResultsFromLocalDb: DeleteSearchResultsFromLocalDb
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

    fun deleteSearchResultsFromLocalDb() {
        val dispatcher = dispatcherProvider?.io ?: EmptyCoroutineContext
        viewModelScope.launch(dispatcher) { deleteSearchResultsFromLocalDb.invoke() }
    }
}