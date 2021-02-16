package com.theteampotato.gifit

import com.theteampotato.gifit.data.local.entity.SearchResult
import com.theteampotato.gifit.data.local.entity.SearchType

object TestUtil {

    fun newSearch() = SearchResult(
        searchString = "Hello World",
        resultGiftUrl = "https://media4.giphy.com/media/f3Fyup5KscPQvfR5sS/giphy.gif?cid=1decc7f7fd9em9bngu7e8rmnspctrjo2pxikswvml123awdk&rid=giphy.gif",
        isFavorite = true,
        translatedValue = "Merhaba Dunya",
        searchType = SearchType.SENTENCE,
        showInHistory = false
    )

    fun newListOfSearch() = listOf(
        SearchResult(
            searchString = "Say Hello",
            resultGiftUrl = "https://media4.giphy.com/media/f3Fyup5KscPQvfR5sS/giphy.gif?cid=1decc7f7fd9em9bngu7e8rmnspctrjo2pxikswvml123awdk&rid=giphy.gif",
            isFavorite = true,
            translatedValue = "Merhaba de!",
            searchType = SearchType.SENTENCE,
            showInHistory = false
        ),
        SearchResult(
            searchString = "Wake up",
            resultGiftUrl = "https://media4.giphy.com/media/f3Fyup5KscPQvfR5sS/giphy.gif?cid=1decc7f7fd9em9bngu7e8rmnspctrjo2pxikswvml123awdk&rid=giphy.gif",
            isFavorite = true,
            translatedValue = "Uyan",
            searchType = SearchType.SENTENCE,
            showInHistory = false
        ),
        SearchResult(
            searchString = "Dance It",
            resultGiftUrl = "https://media4.giphy.com/media/f3Fyup5KscPQvfR5sS/giphy.gif?cid=1decc7f7fd9em9bngu7e8rmnspctrjo2pxikswvml123awdk&rid=giphy.gif",
            isFavorite = true,
            translatedValue = "Dans Et",
            searchType = SearchType.SENTENCE,
            showInHistory = false
        )
    )

}