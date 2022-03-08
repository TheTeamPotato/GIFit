package com.theteampotato.gifit.domain.usecase

import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import com.theteampotato.gifit.data.local.repository.GIFitLocalRepository
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class IsSearchResultExist @Inject constructor(
    private val localRepository: GIFitLocalRepository
) : BaseUseCase() {
    operator fun invoke(searchText: String): Flow<SearchResultEntity?> {
        Timber.d("invoke")
        return localRepository.getSearchResultEntry(searchText)
    }
}