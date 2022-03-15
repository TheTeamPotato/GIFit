package com.theteampotato.gifit.data

import android.content.Context
import android.content.res.Configuration
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.theteampotato.gifit.data.datastore.APP_LANGUAGE
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class LocaleManager @Inject constructor(
    private val activityContext: Context,
    private val preferencesDataStore: DataStore<Preferences>
) {

    suspend fun setNewLocale(languageCode: String) {
        preferencesDataStore.edit { it[APP_LANGUAGE] = languageCode }
        updateResources(languageCode)
    }

    suspend fun setLocale() = updateResources(getLanguage())

    private suspend fun getLanguage() =
        preferencesDataStore.data.map { it[APP_LANGUAGE] ?: getSupportedLanguage() }.first()

    private fun getSupportedLanguage(): String {
        val isSupportDeviceLanguage = LanguageType.values().any { it.languageCode == DeviceManager.language }

        return if (isSupportDeviceLanguage) DeviceManager.language
        else LanguageType.ENGLISH.languageCode
    }

    private fun updateResources(language: String) {
        activityContext.let {
            val locale = Locale(language)
            Locale.setDefault(locale)

            val config: Configuration = it.resources?.configuration!!
            config.setLocale(locale)
            config.setLayoutDirection(locale)

            it.resources?.updateConfiguration(config, it.resources?.displayMetrics)
        }
    }

}