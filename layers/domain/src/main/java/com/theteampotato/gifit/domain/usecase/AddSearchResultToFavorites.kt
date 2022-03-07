package com.theteampotato.gifit.domain.usecase

import com.theteampotato.gifit.data.local.repository.GIFitLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class AddSearchResultToFavorites @Inject constructor(
    private val localRepository: GIFitLocalRepository
) : BaseUseCase() {

    operator fun invoke(id: Long) {
        localRepository.favoriteLastSearchResult(id)
    }

}