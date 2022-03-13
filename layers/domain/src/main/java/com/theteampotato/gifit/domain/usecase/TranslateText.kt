package com.theteampotato.gifit.domain.usecase

import android.content.Context
import com.theteampotato.gifit.translate.GoogleMLKitTranslator
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import timber.log.Timber

class TranslateText @Inject constructor(
    context: Context,
    private val translator: GoogleMLKitTranslator
) : BaseUseCase() {

//    init {
//        translator.initialize(context)
//    }

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

    fun releaseResource() = translator.destroy()

}