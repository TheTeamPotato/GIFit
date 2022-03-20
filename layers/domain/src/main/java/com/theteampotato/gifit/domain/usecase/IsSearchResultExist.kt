package com.theteampotato.gifit.domain.usecase

import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import com.theteampotato.gifit.data.local.repository.GIFitLocalRepository

import javax.inject.Inject

import kotlinx.coroutines.flow.Flow

class IsSearchResultExist @Inject constructor(
    private val localRepository: GIFitLocalRepository
) : BaseUseCase() {
    operator fun invoke(searchText: String): Flow<SearchResultEntity?> = localRepository.getSearchResultEntry(searchText)
}