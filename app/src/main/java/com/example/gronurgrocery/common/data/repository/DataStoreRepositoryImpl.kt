package com.example.gronurgrocery.common.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gronurgrocery.common.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

class DataStoreRepositoryImpl(context: Context) : DataStoreRepository {

    private object PreferencesKey {
        val onboardingKey = booleanPreferencesKey(name = "on_boarding_completed")
        val userTokenKey = stringPreferencesKey(name = "user_token")
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnboardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onboardingKey] = completed
        }
    }

    override fun readOnboardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onboardingState = preferences[PreferencesKey.onboardingKey] ?: false
                onboardingState
            }
    }

    override suspend fun saveUserToken(token: String?) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.userTokenKey] = token ?: ""
        }
    }

    override fun readUserToken(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val token = preferences[PreferencesKey.userTokenKey] ?: ""
                token
            }
    }
}