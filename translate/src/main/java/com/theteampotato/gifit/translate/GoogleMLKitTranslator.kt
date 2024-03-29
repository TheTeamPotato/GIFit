package com.theteampotato.gifit.translate

import android.content.Context

import com.google.mlkit.common.MlKit
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

import timber.log.Timber

class GoogleMLKitTranslator {

    private lateinit var translator: Translator
    private lateinit var options: TranslatorOptions

    private val conditions = DownloadConditions.Builder().build()

    private var isInitialized = false

    fun initialize(context: Context, sourceLanguageCode: String) {
        if (!isInitialized) {
            try {
                MlKit.initialize(context)
                isInitialized = true
            } catch (exception: Exception) {
                Timber.e(exception)
            }

            options = TranslatorOptions.Builder()
                .setSourceLanguage(sourceLanguageCode)
                .setTargetLanguage(TranslateLanguage.ENGLISH)
                .build()

            translator = Translation.getClient(options)
        }
    }

    fun translate(
        value: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {
            translator.translate(value)
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure)
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    fun destroy() = translator.close()

    /**
     * Language models are around 30MB, so don't download them unnecessarily, and
     * only download them using Wi-Fi unless the user has specified otherwise. You
     * should also delete unneeded models.
     */
    fun downloadModels(onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        try {
            translator.downloadModelIfNeeded(conditions)
                .addOnCompleteListener {
                    if (it.isSuccessful)
                        onSuccess()
                    else {
                        onFailure(it.exception!!)
                        throw it.exception!!
                    }
                }
        } catch (e: Exception) {
            throw e
        }
    }
}