package com.example.textilejobs.core.data.repository

import com.example.textilejobs.core.datastore.TjPreferenceDatasource
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(private val tjPreferenceDatasource: TjPreferenceDatasource): UserDataRepository {
    override suspend fun setIsUserLoggedIn(isUserLoggedIn: Boolean) {
        tjPreferenceDatasource.setIsLoggedIn(isUserLoggedIn)
    }

    override suspend fun getIsUserLoggedIn(): Boolean {
        return tjPreferenceDatasource.getIsLoggedIn()
    }

    override suspend fun setIsLanguageChosen(isLanguageChosen: Boolean) {
        tjPreferenceDatasource.setIsLanguageChosen(isLanguageChosen)
    }

    override suspend fun getIsLanguageChosen(): Boolean {
        return tjPreferenceDatasource.getIsLanguageChosen()
    }

    override suspend fun setUserAuthKey(authKey: String) {
        tjPreferenceDatasource.setUserAuthKey(authKey)
    }

    override suspend fun getUserAuthKey(): String {
        return tjPreferenceDatasource.getUserAuthKey()
    }
}