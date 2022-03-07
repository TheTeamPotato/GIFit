package com.theteampotato.gifit.home.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText

import com.theteampotato.gifit.domain.usecase.GetSearchResult
import com.theteampotato.gifit.home.viewmodel.SearchViewModel
import com.theteampotato.gifit.testing.getOrAwaitValue
import com.theteampotato.gifit.ui.GIFitTheme

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test

import javax.inject.Inject

@HiltAndroidTest
class HomeViewTest {

    @get:Rule val composeTestRule = createComposeRule()
    @get:Rule var hiltRule = HiltAndroidRule(this)
    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var getSearchResult: GetSearchResult

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setup() {
        hiltRule.inject()

        searchViewModel = SearchViewModel(getSearchResult = getSearchResult)

        composeTestRule.setContent {
            GIFitTheme {
                SearchScreen(searchViewModel = searchViewModel) {}
            }
        }
    }

    @Test
    fun placeholder_of_GIFitSearchBar_does_exist() {
        val expectedText = "Search..."
        composeTestRule.onNodeWithText(expectedText).assertExists()
    }

    @Test
    fun translatedText_of_ResultCard_should_be_displayed_true() {
        val givenValue = "Ofis"
        val expectedValue = "Office"

        searchViewModel.searchKeyword(givenValue)
        searchViewModel.mSearchResultLiveData.getOrAwaitValue()

        composeTestRule.onNodeWithText(expectedValue).assertIsDisplayed()
    }

}