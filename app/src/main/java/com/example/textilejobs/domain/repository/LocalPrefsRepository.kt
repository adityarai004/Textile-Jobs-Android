package com.example.textilejobs.domain.repository

interface LocalPrefsRepository {
    suspend fun getBoolean(key: String): Boolean
    suspend fun getInt(key: String): Int
    suspend fun getString(key: String): String
    suspend fun setString(key: String, value: String)
    suspend fun setBool(key: String, value: Boolean)
    suspend fun setInt(key: String, value: Int)
    suspend fun getUserAuthKey(): String
    suspend fun setUserAuthKey(authKey: String)
}