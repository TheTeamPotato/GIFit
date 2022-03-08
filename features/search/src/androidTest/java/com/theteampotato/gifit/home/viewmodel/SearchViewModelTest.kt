package com.theteampotato.gifit.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.google.common.truth.Truth.assertThat
import com.theteampotato.gifit.domain.usecase.*

import com.theteampotato.gifit.testing.DispatcherProvider

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher

import javax.inject.Inject

import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchViewModelTest {

    @get:Rule var hiltRule = HiltAndroidRule(this)
    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject lateinit var addSearchResultEntry: AddSearchResultEntry
    @Inject lateinit var addSearchResultToFavorites: AddSearchResultToFavorites
    @Inject lateinit var getSearchResult: GetSearchResult
    @Inject lateinit var isSearchResultExist: IsSearchResultExist
    @Inject lateinit var removeSearchResultFromFavorites: RemoveSearchResultFromFavorites

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setup() {
        hiltRule.inject()

        Dispatchers.setMain(StandardTestDispatcher())

        searchViewModel = SearchViewModel(
            dispatcherProvider = DispatcherProvider(
                io = Dispatchers.Main,
                ui = Dispatchers.Main,
                default = Dispatchers.Main
            ),
            addSearchResultEntry = addSearchResultEntry,
            addSearchResultToFavorites = addSearchResultToFavorites,
            getSearchResult = getSearchResult,
            isSearchResultExist = isSearchResultExist,
            removeSearchResultFromFavorites = removeSearchResultFromFavorites
        )
    }

    @Test
    fun keyword_should_return_expectedResult() = runTest {
        val expectedResult = "Moral"

        searchViewModel.searchKeyword("Ahlaki")

        val searchResult = searchViewModel.searchResultState.value
        val actualResult = searchResult?.translatedText

        assertThat(actualResult).matches(expectedResult)
    }

    @Test
    fun homeViewModel_is_not_null() {
        assertThat(searchViewModel).isNotNull()
    }

}