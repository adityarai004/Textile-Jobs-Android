package com.example.textilejobs.core.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.textilejobs.core.network.ktor.AuthService
import com.example.textilejobs.auth.data.repository.AuthRepositoryImpl
import com.example.textilejobs.auth.domain.repository.AuthRepository
import com.example.textilejobs.core.data.repository.UserDataRepository
import com.example.textilejobs.core.data.repository.UserDataRepositoryImpl
import com.example.textilejobs.core.datastore.TjPreferenceDatasource
import com.example.textilejobs.core.network.ktor.HomeService
import com.example.textilejobs.home.data.repository.HomeRepositoryImpl
import com.example.textilejobs.home.domain.repository.HomeRepository
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
    ): TjPreferenceDatasource = TjPreferenceDatasource(dataStore)

    @Provides
    @Singleton
    fun provideUserDataRepository(
        preferenceDatasource: TjPreferenceDatasource
    ): UserDataRepository = UserDataRepositoryImpl(preferenceDatasource)

    @Provides
    @Singleton
    fun provideAuthRepository(
        authService: AuthService,
    ): AuthRepository = AuthRepositoryImpl(authService)

    @Provides
    @Singleton
    fun provideHomeRepository(homeService: HomeService): HomeRepository =
        HomeRepositoryImpl(homeService)
}