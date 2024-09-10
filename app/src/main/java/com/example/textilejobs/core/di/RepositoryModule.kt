package com.example.textilejobs.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.textilejobs.data.datasources.AuthService
import com.example.textilejobs.data.repository.AuthRepositoryImpl
import com.example.textilejobs.data.repository.LocalPrefsRepositoryImpl
import com.example.textilejobs.domain.repository.AuthRepository
import com.example.textilejobs.domain.repository.LocalPrefsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authService: AuthService,
    ): AuthRepository {
        return AuthRepositoryImpl(authService)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("textile_jobs_prefs")
        }
    }

    @Provides
    @Singleton
    fun provideLocalRepository(dataStore: DataStore<Preferences>): LocalPrefsRepository {
        return LocalPrefsRepositoryImpl(dataStore)
    }
}