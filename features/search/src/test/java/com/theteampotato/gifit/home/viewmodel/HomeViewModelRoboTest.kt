package com.theteampotato.gifit.home.viewmodel

//@RunWith(RobolectricTestRunner::class)
//@HiltAndroidTest
//@Config(application = HiltTestApplication::class)
//@LooperMode(LooperMode.Mode.PAUSED)
//@RunWith(AndroidJUnit4::class)
class HomeViewModelRoboTest {

    //@get:Rule val coroutineTestRule = CoroutineTestRule()
    //@get:Rule var hiltRule = HiltAndroidRule(this)
    //@get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    /*@Inject
    lateinit var getSearchResult: GetSearchResult*/

    /*@Inject
    lateinit var translator: GoogleMLKitTranslator
    @Inject
    lateinit var service: GiphyService*/

    //private lateinit var homeViewModel: HomeViewModel

    /*@Before
    fun setup() {
        hiltRule.inject()

        *//*getSearchResult = GetSearchResult(
            context = ApplicationProvider.getApplicationContext(),
            translator = translator,
            giphyService = service
        )*//*

        homeViewModel = HomeViewModel(
            *//*dispatcherProvider = DispatcherProvider(
                io = coroutineTestRule.dispatcher,
                ui = coroutineTestRule.dispatcher,
                default = coroutineTestRule.dispatcher
            ),*//*
            getSearchResult = getSearchResult
        )
    }*/

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

   /* @Test
    fun getSearchResult_is_not_null() {
        assertThat(getSearchResult).isNotNull()
    }*/

}