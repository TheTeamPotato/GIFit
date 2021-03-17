package com.theteampotato.gifit.history.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import com.theteampotato.gifit.domain.usecase.GetHistoryList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyList: GetHistoryList
) : ViewModel() {

    private lateinit var historyListLiveData: LiveData<List<SearchResultEntity>>
    val mHistoryListLiveData by lazy { historyListLiveData }

    fun getListOfHistory() = viewModelScope.launch {
        historyListLiveData = historyList()
    }

}