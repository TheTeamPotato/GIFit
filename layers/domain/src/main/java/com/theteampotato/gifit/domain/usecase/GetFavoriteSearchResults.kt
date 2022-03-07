package com.theteampotato.gifit.domain.usecase

import androidx.lifecycle.LiveData
import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import com.theteampotato.gifit.data.local.repository.GIFitLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFavoriteSearchResults @Inject constructor(
    private val localRepository: GIFitLocalRepository
) : BaseUseCase() {

    suspend operator fun invoke() : LiveData<List<SearchResultEntity>> {
        return localRepository.getAllFavoritesResults()
//return localRepository.getAll()
//        return withContext(Dispatchers.IO) {
//            localRepository.getAllFavoritesResults().value
//        }
    }
}