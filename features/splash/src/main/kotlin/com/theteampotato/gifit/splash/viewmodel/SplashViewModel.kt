package com.theteampotato.gifit.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theteampotato.gifit.domain.usecase.GetSelectedLanguage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSelectedLanguage: GetSelectedLanguage
) : ViewModel() {

    suspend fun getIsSelectedLanguage() =
        withContext(viewModelScope.coroutineContext) {
            getSelectedLanguage.invoke()
        }

}