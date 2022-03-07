package com.theteampotato.gifit.domain.usecase

import android.content.Context
import com.theteampotato.gifit.data.remote.repository.GIFitRemoteRepository
import com.theteampotato.gifit.domain.mapper.toSearchResult
import com.theteampotato.gifit.domain.model.SearchResult
import com.theteampotato.gifit.translate.GoogleMLKitTranslator

import javax.inject.Inject

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import timber.log.Timber

class GetSearchResult @Inject constructor(
    context: Context,
    private val translator: GoogleMLKitTranslator,
    private val remoteRepository: GIFitRemoteRepository
) : BaseUseCase() {

    init {
        translator.initialize(context)
    }

    suspend operator fun invoke(keyword: String): SearchResult? {
        val translatedText = getTranslatedText(keyword) ?: return null
        return getGiphyResult(translatedText).toSearchResult(translatedText)
    }

    private suspend fun getTranslatedText(keyword: String) = suspendCoroutine<String?> { continuation ->
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

    private suspend fun getGiphyResult(text: String) = withContext(Dispatchers.IO) { remoteRepository.search(text) }

}