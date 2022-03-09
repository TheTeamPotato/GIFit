package com.theteampotato.gifit.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test

import com.google.common.truth.Truth.assertThat
import com.theteampotato.gifit.domain.usecase.*

import com.theteampotato.gifit.testing.DispatcherProvider

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.*
import kotlinx.coroutines.test.StandardTestDispatcher

import javax.inject.Inject

import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import timber.log.Timber

import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@HiltAndroidTest
class SearchViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject lateinit var addSearchResultEntry: AddSearchResultEntry
    @Inject lateinit var addSearchResultToFavorites: AddSearchResultToFavorites
    @Inject lateinit var isSearchResultExist: IsSearchResultExist
    @Inject lateinit var removeSearchResultFromFavorites: RemoveSearchResultFromFavorites
    @Inject lateinit var getSearchResult: GetSearchResult

    private lateinit var searchViewModel: SearchViewModel

    @Before
    @OptIn(ExperimentalCoroutinesApi::class)
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
    @OptIn(ExperimentalTime::class, ExperimentalCoroutinesApi::class)
    fun keyword_should_return_expectedResult() = runTest {
        val expectedResult = "School"

        searchViewModel.searchKeyword("Okul")?.test(timeout = 15.seconds) {
            val searchResult = awaitItem()
            val actualResult = searchResult?.translatedText
            assertThat(actualResult).matches(expectedResult)
        }
    }

    @Test
    @OptIn(ExperimentalTime::class, ExperimentalCoroutinesApi::class)
    fun wrong_expected_result_does_not_match_with_actualResult() = runTest {
        val expectedResult = "Student"

        searchViewModel.searchKeyword("Araba")?.test(timeout = 15.seconds) {
            val searchResult = awaitItem()
            val actualResult = searchResult?.translatedText
            assertThat(actualResult).doesNotMatch(expectedResult)
        }
    }

    @Test
    fun homeViewModel_is_not_null() {
        assertThat(searchViewModel).isNotNull()
    }

}