package com.theteampotato.gifit.home.viewmodel

import android.os.Looper
import android.os.Looper.getMainLooper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.theteampotato.gifit.data.remote.GiphyService

import com.theteampotato.gifit.domain.usecase.GetSearchResult
import com.theteampotato.gifit.home.viewmodel.HomeViewModel
import com.theteampotato.gifit.testing.CoroutineTestRule
import com.theteampotato.gifit.testing.getOrAwaitValue
import com.theteampotato.gifit.testing.getValue
import com.theteampotato.gifit.translate.GoogleMLKitTranslator

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

import javax.inject.Inject

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode

@RunWith(RobolectricTestRunner::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
//@LooperMode(LooperMode.Mode.PAUSED)
//@RunWith(AndroidJUnit4::class)
class HomeViewModelRoboTest {

    //@get:Rule val coroutineTestRule = CoroutineTestRule()
    @get:Rule var hiltRule = HiltAndroidRule(this)
    //@get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var getSearchResult: GetSearchResult

    /*@Inject
    lateinit var translator: GoogleMLKitTranslator
    @Inject
    lateinit var service: GiphyService*/

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        hiltRule.inject()

        /*getSearchResult = GetSearchResult(
            context = ApplicationProvider.getApplicationContext(),
            translator = translator,
            giphyService = service
        )*/

        homeViewModel = HomeViewModel(
            /*dispatcherProvider = DispatcherProvider(
                io = coroutineTestRule.dispatcher,
                ui = coroutineTestRule.dispatcher,
                default = coroutineTestRule.dispatcher
            ),*/
            getSearchResult = getSearchResult
        )
    }

    // FIXME: Caused by: com.google.mlkit.common.MlKitException: No existing model file
    //  Find a way to solve this exception
    /*@Test
    fun keyword_should_return_expectedResult() {
        val expectedResult = "Moral"

        homeViewModel.searchKeyword("Ahlaki")

        val searchResult = getValue(homeViewModel.mSearchResultLiveData)
        shadowOf(getMainLooper()).idle()

        val actualResult = searchResult?.translatedText

        assertThat(actualResult).matches(expectedResult)
    }*/

    @Test
    fun getSearchResult_is_not_null() {
        assertThat(getSearchResult).isNotNull()
    }

}