package com.theteampotato.gifit.domain.usecase

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*
import javax.inject.Inject

class ReadText @Inject constructor(context: Context) : BaseUseCase() {

    private lateinit var textToSpeech: TextToSpeech

    init {
        textToSpeech = TextToSpeech(context) { status ->
            if (status != TextToSpeech.ERROR)
                textToSpeech.language = Locale.US
        }
    }

    operator fun invoke(text: String) = textToSpeech.apply {
        setPitch(1f)
        setSpeechRate(1f)
        speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun releaseResource() = textToSpeech.shutdown()

}