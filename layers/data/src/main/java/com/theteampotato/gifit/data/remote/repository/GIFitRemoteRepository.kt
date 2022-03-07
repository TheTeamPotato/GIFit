package com.theteampotato.gifit.data.remote.repository

import com.theteampotato.gifit.data.remote.GiphyService
import javax.inject.Inject

class GIFitRemoteRepository @Inject constructor(private val giphyService: GiphyService) {
    suspend fun search(text: String) = giphyService.search(text)
}