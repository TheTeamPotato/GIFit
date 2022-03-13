package com.theteampotato.gifit.domain.usecase

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.theteampotato.gifit.data.datastore.IS_LANGUAGE_SELECTED
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetIsSelectedLanguage @Inject constructor(private val preferencesDataStore: DataStore<Preferences>) {

    suspend operator fun invoke(): Boolean {
        return preferencesDataStore.data.map {
            it[IS_LANGUAGE_SELECTED] ?: false
        }.first()
    }

}