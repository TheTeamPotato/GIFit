package com.theteampotato.gifit.domain.usecase

import com.theteampotato.gifit.data.remote.repository.GIFitRemoteRepository
import com.theteampotato.gifit.domain.mapper.toSearchResult

import javax.inject.Inject

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSearchResult @Inject constructor(private val remoteRepository: GIFitRemoteRepository) : BaseUseCase() {

    suspend operator fun invoke(text: String) = getGiphyResult(text).toSearchResult(text)

    private suspend fun getGiphyResult(text: String) = withContext(Dispatchers.IO) { remoteRepository.search(text) }

}