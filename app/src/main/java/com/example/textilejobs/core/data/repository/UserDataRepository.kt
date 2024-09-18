package com.example.textilejobs.core.data.repository

interface UserDataRepository {
    suspend fun setIsUserLoggedIn(isUserLoggedIn: Boolean)
    suspend fun getIsUserLoggedIn(): Boolean
    suspend fun setIsLanguageChosen(isLanguageChosen: Boolean)
    suspend fun getIsLanguageChosen(): Boolean
    suspend fun setUserAuthKey(authKey: String)
    suspend fun getUserAuthKey(): String
}