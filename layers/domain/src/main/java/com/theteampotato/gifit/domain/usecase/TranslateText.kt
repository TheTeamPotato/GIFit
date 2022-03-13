package com.theteampotato.gifit.domain.usecase

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.theteampotato.gifit.data.datastore.SELECTED_LANGUAGE
import com.theteampotato.gifit.translate.GoogleMLKitTranslator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import timber.log.Timber

class TranslateText @Inject constructor(
    context: Context,
    preferencesDataStore: DataStore<Preferences>,
    private val translator: GoogleMLKitTranslator
) : BaseUseCase() {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    init {
        scope.launch {
            preferencesDataStore.data.map { it[SELECTED_LANGUAGE] }.first()?.let {
                translator.initialize(context, it)
                Timber.d("translator initialized with source language code -> $it")
            }
        }
    }

    suspend operator fun invoke(text: String) = suspendCoroutine<String?> { continuation ->
        translator.translate(value = text, { response ->
            continuation.resume(response)
        },
            {
                Timber.e(it)
                continuation.resume(null)
            }
        )
    }

    fun releaseResource() {
        scope.cancel()
        translator.destroy()
    }

}