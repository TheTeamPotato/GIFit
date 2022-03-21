package com.theteampotato.gifit.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.theteampotato.gifit.domain.usecase.GetSelectedLanguage

import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

import kotlinx.coroutines.withContext

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSelectedLanguage: GetSelectedLanguage
) : ViewModel() {

    suspend fun getIsSelectedLanguage() =
        withContext(viewModelScope.coroutineContext) {
            getSelectedLanguage.invoke()
        }

}