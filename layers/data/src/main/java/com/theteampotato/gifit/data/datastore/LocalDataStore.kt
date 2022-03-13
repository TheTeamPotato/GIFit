package com.theteampotato.gifit.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

val SELECTED_LANGUAGE = stringPreferencesKey("selected_language")
