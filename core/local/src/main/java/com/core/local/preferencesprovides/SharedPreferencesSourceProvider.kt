package com.core.local.preferencesprovides

import android.content.Context
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.core.local.LocalSourceProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class SharedPreferencesSourceProvider(
    context: Context
): LocalSourceProvider {

    private val Context.datastore by preferencesDataStore(PREF_NAME)

    private val datastore = context.datastore

    private object PreferenceKey {
        val KEY = stringPreferencesKey(KEY_NAME)
    }

    override suspend fun saveCityState(city: String) {
        datastore.edit { preferences ->
            preferences[PreferenceKey.KEY] = city
        }
    }

    override fun readCityState(): Flow<String> {
        return datastore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val city = preferences[PreferenceKey.KEY] ?: STRING_EMPTY
                city
            }
    }

    companion object{
        private const val PREF_NAME = "city_pref"
        private const val KEY_NAME = "city_key"
        private const val STRING_EMPTY = ""
    }
}