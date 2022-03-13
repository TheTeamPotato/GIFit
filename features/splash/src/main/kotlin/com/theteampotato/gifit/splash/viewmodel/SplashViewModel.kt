package com.theteampotato.gifit.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theteampotato.gifit.domain.usecase.GetIsSelectedLanguage
import com.theteampotato.gifit.domain.usecase.SetIsSelectedLanguage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getIsSelectedLanguage: GetIsSelectedLanguage,
    private val setIsSelectedLanguage: SetIsSelectedLanguage
) : ViewModel() {

    suspend fun getIsSelectedLanguage() =
        withContext(viewModelScope.coroutineContext) {
            getIsSelectedLanguage.invoke()
        }

    fun setIsSelectedLanguage(isSelectedLanguage: Boolean) {
        viewModelScope.launch {
            setIsSelectedLanguage.invoke(isSelectedLanguage)
        }
    }

}