package com.theteampotato.gifit.domain.usecase

import com.theteampotato.gifit.data.local.repository.GIFitLocalRepository
import javax.inject.Inject

class AddSearchResultToFavorites @Inject constructor(
    private val localRepository: GIFitLocalRepository
) : BaseUseCase() {

    operator fun invoke(id: Long) {
        localRepository.favoriteLastSearchResult(id)
    }

}