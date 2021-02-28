package com.theteampotato.gifit.translate

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import timber.log.Timber

class GoogleMLKitTranslator : ITranslator {

    private lateinit var translator: Translator
    private lateinit var options: TranslatorOptions

    private val conditions = DownloadConditions.Builder().build()

    fun initialize() {
        options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.TURKISH)
            .setTargetLanguage(TranslateLanguage.ENGLISH)
            .build()

        translator = Translation.getClient(options)
    }

    fun translate(
        value: String,
        onSuccess: (String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        try {
            downloadModels(onSuccess = {
                try {
                    translator.translate(value)
                        .addOnSuccessListener(onSuccess)
                        .addOnFailureListener(onFailure)
                } catch (e: Exception) {
                    onFailure(e)
                }
            }, onFailure = onFailure)
        } catch (e: Exception) {
            Timber.e("Fukking Exception is $e")
            //throw e
        }
    }

    fun destroy() {
        translator.close()
    }

    /**
     * // TODO: Warn users about this issue in the app
     * Language models are around 30MB, so don't download them unnecessarily, and
     * only download them using Wi-Fi unless the user has specified otherwise. You
     * should also delete unneeded models.
     */
    private fun downloadModels(onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        try {
            translator.downloadModelIfNeeded(conditions)
                .addOnCompleteListener {
                    if (it.isSuccessful)
                        onSuccess()
                    else
                    //onFailure(it.exception!!)
                        throw it.exception!!
                }
        } catch (e: Exception) {
            throw e
        }
    }
}