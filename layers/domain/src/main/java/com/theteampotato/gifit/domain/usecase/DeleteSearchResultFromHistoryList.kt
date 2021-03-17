package com.theteampotato.gifit.domain.usecase

import com.theteampotato.gifit.data.local.repository.SearchResultRepository
import javax.inject.Inject

class DeleteSearchResultFromHistoryList @Inject constructor(
    private val historyLocalService: SearchResultRepository
) : BaseUseCase() {

}