package com.theteampotato.gifit.translate

import androidx.test.platform.app.InstrumentationRegistry

import com.google.common.truth.Truth.assertThat

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

import kotlinx.coroutines.*

import org.junit.Before
import org.junit.Test

import timber.log.Timber

class GoogleMLKitTranslatorTest {

    private lateinit var translator: GoogleMLKitTranslator

    @Before
    fun setup() {
        translator = GoogleMLKitTranslator()
        translator.initialize(InstrumentationRegistry.getInstrumentation().targetContext)

        Timber.plant(Timber.DebugTree())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun translate_turkish_test_to_english_should_return_true() = runBlocking {
        val givenValue = "Merhaba"
        val expectedValue = "Hi"

        val translatedText = suspendCoroutine<String> { continuation ->
            translator.translate(
                value = givenValue,
                onSuccess = {
                    Timber.d("Translated text is $it")
                    continuation.resume(it)
                },
                onFailure = {
                    Timber.e("Exception is ${it.message}")
                    continuation.resume(it.message ?: "Wow, something is wrong!")
                }
            )
        }

        assertThat(translatedText).matches(expectedValue)
    }

    @Test
    fun translate_turkish_test_to_english_should_return_false() = runBlocking {
        val givenValue = "Merhaba"
        val notExpectedValue = "Good morning"

        val translatedText = suspendCoroutine<String> { continuation ->
            translator.translate(
                value = givenValue,
                onSuccess = {
                    Timber.d("Translated text is $it")
                    continuation.resume(it)
                },
                onFailure = {
                    Timber.e("Exception is ${it.message}")
                    continuation.resume(it.message ?: "Wow, something is wrong!")
                }
            )
        }

        assertThat(translatedText).doesNotMatch(notExpectedValue)
    }

}