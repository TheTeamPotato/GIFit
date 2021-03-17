package com.theteampotato.gifit.domain.usecase

import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import com.theteampotato.gifit.data.local.entity.SearchType
import com.theteampotato.gifit.data.local.repository.SearchResultRepository
import com.theteampotato.gifit.data.remote.GiphyService
import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.translate.GoogleMLKitTranslator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetSearchResult @Inject constructor(
    private val translator: GoogleMLKitTranslator,
    private val giphyService: GiphyService,
    private val historyLocalService: SearchResultRepository
) : BaseUseCase() {

    init {
        translator.initialize()
    }

    suspend operator fun invoke(keyword: String): SearchResult? {
        val translatedText = getTranslatedText(keyword) ?: return null
        val giphyURL = getGiphyURL(translatedText)

        historyLocalService.insert(
            searchResultEntity = SearchResultEntity(
                searchString = keyword,
                resultGiftUrl = giphyURL.data.first().images.original.url,
                isFavorite = false,
                translatedValue = translatedText,
                searchType = SearchType.SENTENCE,
                showInHistory = true
            )
        )

        return SearchResult(translatedText, giphyURL)
    }

    private suspend fun getTranslatedText(keyword: String) =
        suspendCoroutine<String?> { continuation ->
            translator.translate(
                value = keyword, { response ->
                    continuation.resume(response)
                },
                {
                    Timber.e(it)
                    continuation.resume(null)
                }
            )
        }

    private suspend fun getGiphyURL(text: String) = async(Dispatchers.IO) {
        return@async giphyService.search(text)
    }.await()

}