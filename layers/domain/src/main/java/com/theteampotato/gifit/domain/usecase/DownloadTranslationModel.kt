package com.theteampotato.gifit.domain.usecase

import android.content.Context

import com.theteampotato.gifit.translate.GoogleMLKitTranslator

import java.lang.Exception
import javax.inject.Inject

class DownloadTranslationModel @Inject constructor(
    private val context: Context,
    private val translator: GoogleMLKitTranslator
) {

    operator fun invoke(sourceLanguageCode: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        translator.initialize(context, sourceLanguageCode)
        translator.downloadModels(onSuccess, onFailure)
    }

}