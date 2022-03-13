package com.theteampotato.gifit.domain.usecase

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.theteampotato.gifit.data.datastore.SELECTED_LANGUAGE
import javax.inject.Inject

class SetSelectedLanguage @Inject constructor(private val preferencesDataStore: DataStore<Preferences>) {

    suspend operator fun invoke(selectedLanguage: String) =
        preferencesDataStore.edit { it[SELECTED_LANGUAGE] = selectedLanguage }

}