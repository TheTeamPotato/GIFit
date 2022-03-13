package com.theteampotato.gifit.language_selection.viewmodel

import androidx.lifecycle.ViewModel
import com.theteampotato.gifit.domain.usecase.DownloadTranslationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LanguageSelectionViewModel @Inject constructor(
    private val downloadTranslationModel: DownloadTranslationModel
) : ViewModel() {

    fun downloadTranslationModel(sourceLanguageCode: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) =
        downloadTranslationModel.invoke(sourceLanguageCode, onSuccess, onFailure)

}