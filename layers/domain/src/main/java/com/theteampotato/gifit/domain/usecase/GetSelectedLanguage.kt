package com.theteampotato.gifit.domain.usecase

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

import com.theteampotato.gifit.data.datastore.SELECTED_LANGUAGE

import javax.inject.Inject

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class GetSelectedLanguage @Inject constructor(private val preferencesDataStore: DataStore<Preferences>) {

    suspend operator fun invoke(): String? = preferencesDataStore.data.map { it[SELECTED_LANGUAGE] }.first()

}