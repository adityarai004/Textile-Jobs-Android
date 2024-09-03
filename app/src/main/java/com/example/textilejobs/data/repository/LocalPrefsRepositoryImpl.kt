package com.example.textilejobs.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.textilejobs.core.constants.LocalPrefsConstants
import com.example.textilejobs.domain.repository.LocalPrefsRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LocalPrefsRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    LocalPrefsRepository {

    override suspend fun setUserAuthKey(authKey: String) {
        dataStore.edit { prefs ->
            prefs[stringPreferencesKey(LocalPrefsConstants.USER_TOKEN)] = authKey
            prefs[booleanPreferencesKey(LocalPrefsConstants.USER_IS_LOGGED_IN)] = true
        }
    }

    override suspend fun getUserAuthKey(): String {
        val preferences = dataStore.data.first()
        return preferences[stringPreferencesKey(LocalPrefsConstants.USER_TOKEN)] ?: ""
    }

    override suspend fun getString(key: String): String {
        val preferences = dataStore.data.first()
        return preferences[stringPreferencesKey(key)] ?: ""

    }

    override suspend fun getBoolean(key: String): Boolean {
        val preferences = dataStore.data.first()
        return preferences[booleanPreferencesKey(key)] ?: false
    }

    override suspend fun getInt(key: String): Int {
        val preferences = dataStore.data.first()
        return preferences[intPreferencesKey(key)] ?: -1
    }

    override suspend fun setBool(key: String, value: Boolean) {
        dataStore.edit { prefs ->
            prefs[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun setInt(key: String, value: Int) {
        dataStore.edit { prefs ->
            prefs[intPreferencesKey(key)] = value
        }
    }

    override suspend fun setString(key: String, value: String) {
        dataStore.edit { prefs ->
            prefs[stringPreferencesKey(key)] = value
        }
    }
}