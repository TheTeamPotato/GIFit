package com.theteampotato.gifit.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import com.google.common.truth.Truth.assertThat

import com.theteampotato.gifit.testing.CoroutineTestRule
import com.theteampotato.gifit.domain.usecase.GetSearchResult
import com.theteampotato.gifit.testing.DispatcherProvider
import com.theteampotato.gifit.testing.getOrAwaitValue

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import javax.inject.Inject

import kotlinx.coroutines.test.runBlockingTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchViewModelTest {

    @get:Rule val coroutineTestRule = CoroutineTestRule()
    @get:Rule var hiltRule = HiltAndroidRule(this)
    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject lateinit var getSearchResult: GetSearchResult

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setup() {
        hiltRule.inject()

//        searchViewModel = SearchViewModel(
//            dispatcherProvider = DispatcherProvider(
//                io = coroutineTestRule.dispatcher,
//                ui = coroutineTestRule.dispatcher,
//                default = coroutineTestRule.dispatcher
//            ),
//            getSearchResult = getSearchResult
//        )
    }

    @Test
    fun keyword_should_return_expectedResult() = coroutineTestRule.runBlockingTest {
        val expectedResult = "Moral"

        searchViewModel.searchKeyword("Ahlaki")

        val searchResult = searchViewModel.mSearchResultLiveData.getOrAwaitValue()
        val actualResult = searchResult?.translatedText

        assertThat(actualResult).matches(expectedResult)
    }

    @Test
    fun homeViewModel_is_not_null() {
        assertThat(searchViewModel).isNotNull()
    }

}