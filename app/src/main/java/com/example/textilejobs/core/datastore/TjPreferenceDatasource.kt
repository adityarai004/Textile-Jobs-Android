package com.example.textilejobs.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.textilejobs.core.constants.LocalPrefsConstants
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TjPreferenceDatasource @Inject constructor(private val userPreferences: DataStore<Preferences>) {
    suspend fun setIsLoggedIn(isLoggedIn: Boolean){
        try{
            userPreferences.edit {
                it[booleanPreferencesKey(LocalPrefsConstants.USER_IS_LOGGED_IN)] = isLoggedIn
            }
        } catch (ioException: IOException){
            Log.e("TjPreferences", "Failed to Update user data ${ioException.message}")
        }
    }

    suspend fun getIsLoggedIn(): Boolean {
        try{
            val prefs = userPreferences.data.first()
            return prefs[booleanPreferencesKey(LocalPrefsConstants.USER_IS_LOGGED_IN)] ?: false
        } catch (ioException: IOException){
            Log.e("TjPreferences", "Failed to Update user data ${ioException.message}")
        }
        return false
    }

    suspend fun setIsLanguageChosen(isLanguageChosen: Boolean){
        try{
            userPreferences.edit {
                it[booleanPreferencesKey(LocalPrefsConstants.IS_LANGUAGE_CHOSEN)] = isLanguageChosen
            }
        } catch (ioException: IOException){
            Log.e("TjPreferences", "Failed to Update user data ${ioException.message}")
        }
    }

    suspend fun getIsLanguageChosen(): Boolean {
        try{
            val prefs = userPreferences.data.first()
            return prefs[booleanPreferencesKey(LocalPrefsConstants.IS_LANGUAGE_CHOSEN)] ?: false
        } catch (ioException: IOException){
            Log.e("TjPreferences", "Failed to Update user data ${ioException.message}")
        }
        return false
    }

    suspend fun setUserAuthKey(authKey: String) {
        try {
            userPreferences.edit { prefs ->
                prefs[stringPreferencesKey(LocalPrefsConstants.USER_TOKEN)] = authKey
                prefs[booleanPreferencesKey(LocalPrefsConstants.USER_IS_LOGGED_IN)] = true
            }
        } catch (ioException: IOException) {
            Log.e("TjPreferences", "Failed to update user preferences", ioException)
        }
    }

    suspend fun getUserAuthKey(): String {
        try {
            val preferences = userPreferences.data.first()
            return preferences[stringPreferencesKey(LocalPrefsConstants.USER_TOKEN)] ?: ""
        } catch (ioException: IOException) {
            Log.e("TjPreferences", "Failed to update user preferences", ioException)
            return ""
        }
    }
}