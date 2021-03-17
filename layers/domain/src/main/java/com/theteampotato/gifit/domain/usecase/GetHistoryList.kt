package com.theteampotato.gifit.domain.usecase

import androidx.lifecycle.LiveData
import com.theteampotato.gifit.data.local.entity.SearchResultEntity
import com.theteampotato.gifit.data.local.repository.SearchResultRepository
import javax.inject.Inject

class GetHistoryList @Inject constructor(
    private val historyLocalService: SearchResultRepository
) : BaseUseCase() {

    suspend operator fun invoke(): LiveData<List<SearchResultEntity>> {
        return historyLocalService.getAll()
    }

}