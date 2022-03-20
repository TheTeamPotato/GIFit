package com.theteampotato.gifit.domain.usecase

import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import com.theteampotato.gifit.data.local.repository.GIFitLocalRepository

import javax.inject.Inject

class AddSearchResultEntry @Inject constructor(
    private val localRepository: GIFitLocalRepository
) : BaseUseCase() {

    suspend operator fun invoke(SearchResultEntity: SearchResultEntity) : Long = localRepository.insert(SearchResultEntity)

}