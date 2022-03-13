package com.theteampotato.gifit.domain.usecase

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.theteampotato.gifit.data.datastore.IS_LANGUAGE_SELECTED
import javax.inject.Inject

class SetIsSelectedLanguage @Inject constructor(private val preferencesDataStore: DataStore<Preferences>) {

    suspend operator fun invoke(isSelected: Boolean) =
        preferencesDataStore.edit { it[IS_LANGUAGE_SELECTED] = isSelected }

}