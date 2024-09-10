package com.example.textilejobs.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
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
        try {
            dataStore.edit { prefs ->
                prefs[stringPreferencesKey(LocalPrefsConstants.USER_TOKEN)] = authKey
                prefs[booleanPreferencesKey(LocalPrefsConstants.USER_IS_LOGGED_IN)] = true
            }
        } catch (ioException: IOException) {
            Log.e("TjPreferences", "Failed to update user preferences", ioException)
        }
    }

    override suspend fun getUserAuthKey(): String {
        try {
            val preferences = dataStore.data.first()
            return preferences[stringPreferencesKey(LocalPrefsConstants.USER_TOKEN)] ?: ""
        } catch (ioException: IOException) {
            Log.e("TjPreferences", "Failed to update user preferences", ioException)
            return ""
        }
    }

    override suspend fun getString(key: String): String {
        try {
            val preferences = dataStore.data.first()
            return preferences[stringPreferencesKey(key)] ?: ""
        } catch (ioException: IOException) {
            Log.e("TjPreferences", "Failed to update user preferences", ioException)
            return ""
        }

    }

    override suspend fun getBoolean(key: String): Boolean {
        try {
            val preferences = dataStore.data.first()
            return preferences[booleanPreferencesKey(key)] ?: false
        } catch (ioException: IOException) {
            Log.e("TjPreferences", "Failed to update user preferences", ioException)
            return false
        }
    }

    override suspend fun getInt(key: String): Int {
        try {
            val preferences = dataStore.data.first()
            return preferences[intPreferencesKey(key)] ?: -1
        } catch (ioException: IOException) {
            Log.e("TjPreferences", "Failed to update user preferences", ioException)
            return -1
        }
    }

    override suspend fun setBool(key: String, value: Boolean) {
        dataStore.edit { prefs ->
            prefs[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun setInt(key: String, value: Int) {
        try {
            dataStore.edit { prefs ->
                prefs[intPreferencesKey(key)] = value
            }
        } catch (ioException: IOException) {
            Log.e("TjPreferences", "Failed to update user preferences", ioException)
        }
    }

    override suspend fun setString(key: String, value: String) {
        try {
            dataStore.edit { prefs ->
                prefs[stringPreferencesKey(key)] = value
            }
        } catch (ioException: IOException) {
            Log.e("TjPreferences", "Failed to update user preferences", ioException)
        }
    }
}