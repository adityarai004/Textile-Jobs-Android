package com.example.textilejobs.core.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.textilejobs.auth.data.datasources.AuthService
import com.example.textilejobs.auth.data.repository.AuthRepositoryImpl
import com.example.textilejobs.auth.domain.repository.AuthRepository
import com.example.textilejobs.core.data.repository.UserDataRepository
import com.example.textilejobs.core.data.repository.UserDataRepositoryImpl
import com.example.textilejobs.core.datastore.TjPreferenceDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTjPreferenceDatasource(
        dataStore: DataStore<Preferences>
    ): TjPreferenceDatasource {
        return TjPreferenceDatasource(dataStore)
    }

    @Provides
    @Singleton
    fun provideUserDataRepository(
        preferenceDatasource: TjPreferenceDatasource
    ): UserDataRepository {
        return UserDataRepositoryImpl(preferenceDatasource)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authService: AuthService,
    ): AuthRepository {
        return AuthRepositoryImpl(authService)
    }

}