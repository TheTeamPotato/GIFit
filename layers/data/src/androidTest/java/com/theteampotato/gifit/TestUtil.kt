package com.theteampotato.gifit

import com.theteampotato.gifit.data.local.entity.SearchResultEntity

object TestUtil {

    fun newSearch() = SearchResultEntity(
        searchText = "Hello World",
        gifUrl = "https://media4.giphy.com/media/f3Fyup5KscPQvfR5sS/giphy.gif?cid=1decc7f7fd9em9bngu7e8rmnspctrjo2pxikswvml123awdk&rid=giphy.gif",
        isFavorite = true,
        translatedText = "Merhaba Dunya",
        showInHistory = false
    )

    fun newListOfSearch() = listOf(
        SearchResultEntity(
            searchText = "Say Hello",
            gifUrl = "https://media4.giphy.com/media/f3Fyup5KscPQvfR5sS/giphy.gif?cid=1decc7f7fd9em9bngu7e8rmnspctrjo2pxikswvml123awdk&rid=giphy.gif",
            isFavorite = true,
            translatedText = "Merhaba de!",
            showInHistory = false
        ),
        SearchResultEntity(
            searchText = "Wake up",
            gifUrl = "https://media4.giphy.com/media/f3Fyup5KscPQvfR5sS/giphy.gif?cid=1decc7f7fd9em9bngu7e8rmnspctrjo2pxikswvml123awdk&rid=giphy.gif",
            isFavorite = true,
            translatedText = "Uyan",
            showInHistory = false
        ),
        SearchResultEntity(
            searchText = "Dance It",
            gifUrl = "https://media4.giphy.com/media/f3Fyup5KscPQvfR5sS/giphy.gif?cid=1decc7f7fd9em9bngu7e8rmnspctrjo2pxikswvml123awdk&rid=giphy.gif",
            isFavorite = true,
            translatedText = "Dans Et",
            showInHistory = false
        )
    )

}