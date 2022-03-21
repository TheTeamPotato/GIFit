package com.theteampotato.gifit.language_selection.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.theteampotato.gifit.domain.usecase.DownloadTranslationModel
import com.theteampotato.gifit.domain.usecase.SetSelectedLanguage

import dagger.hilt.android.lifecycle.HiltViewModel

import java.lang.Exception
import javax.inject.Inject

import kotlinx.coroutines.launch

@HiltViewModel
class LanguageSelectionViewModel @Inject constructor(
    private val downloadTranslationModel: DownloadTranslationModel,
    private val setSelectedLanguage: SetSelectedLanguage
) : ViewModel() {

    fun downloadTranslationModel(sourceLanguageCode: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) =
        downloadTranslationModel.invoke(sourceLanguageCode, onSuccess, onFailure)

    fun setSelectedLanguage(selectedLanguage: String) = viewModelScope.launch {
        setSelectedLanguage.invoke(selectedLanguage)
    }

}