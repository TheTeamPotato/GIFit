package com.theteampotato.gifit.domain.usecase

import com.theteampotato.gifit.data.local.repository.GIFitLocalRepository
import javax.inject.Inject

class GetFavoriteSearchResults @Inject constructor(
    private val localRepository: GIFitLocalRepository
) : BaseUseCase() {
    operator fun invoke() = localRepository.getAllFavoritesResults()
}