package com.theteampotato.gifit.domain.usecase

import com.theteampotato.gifit.data.local.repository.GIFitLocalRepository
import javax.inject.Inject

class RemoveFavoriteSearchResult @Inject constructor(
    private val localRepository: GIFitLocalRepository
) : BaseUseCase() {

    suspend operator fun invoke(id: Long) = localRepository.removeSearchResultFromFavorites(id)

}