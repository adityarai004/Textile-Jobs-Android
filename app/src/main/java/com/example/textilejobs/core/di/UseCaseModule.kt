package com.example.textilejobs.core.di

import com.example.textilejobs.domain.repository.AuthRepository
import com.example.textilejobs.domain.repository.LocalPrefsRepository
import com.example.textilejobs.domain.usecase.GetBooleanUseCase
import com.example.textilejobs.domain.usecase.GetStringUseCase
import com.example.textilejobs.domain.usecase.GetUserAuthKeyUseCase
import com.example.textilejobs.domain.usecase.LoginUseCase
import com.example.textilejobs.domain.usecase.SetBooleanUseCase
import com.example.textilejobs.domain.usecase.SetIntUseCase
import com.example.textilejobs.domain.usecase.SetStringUseCase
import com.example.textilejobs.domain.usecase.SetUserAuthTokenUseCase
import com.example.textilejobs.domain.usecase.SignInWithGoogleUseCase
import com.example.textilejobs.domain.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase =
        LoginUseCase(authRepository)

    @Provides
    @Singleton
    fun provideGetUserAuthKeyUseCase(localPrefsRepository: LocalPrefsRepository): GetUserAuthKeyUseCase =
        GetUserAuthKeyUseCase(localPrefsRepository)

    @Provides
    @Singleton
    fun provideSetUserAuthKeyUseCase(localPrefsRepository: LocalPrefsRepository): SetUserAuthTokenUseCase =
        SetUserAuthTokenUseCase(localPrefsRepository)

    @Provides
    @Singleton
    fun provideGetBooleanUseCase(localPrefsRepository: LocalPrefsRepository): GetBooleanUseCase =
        GetBooleanUseCase(localPrefsRepository)

    @Provides
    @Singleton
    fun provideGetStringUseCase(localPrefsRepository: LocalPrefsRepository): GetStringUseCase =
        GetStringUseCase(localPrefsRepository)

    @Provides
    @Singleton
    fun provideSetBoolUseCase(localPrefsRepository: LocalPrefsRepository): SetBooleanUseCase =
        SetBooleanUseCase(localPrefsRepository)

    @Provides
    @Singleton
    fun provideSetStringUseCase(localPrefsRepository: LocalPrefsRepository): SetStringUseCase =
        SetStringUseCase(localPrefsRepository)

    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase =
        SignUpUseCase(authRepository)

    @Provides
    @Singleton
    fun provideSetIntUseCase(localPrefsRepository: LocalPrefsRepository): SetIntUseCase =
        SetIntUseCase(localPrefsRepository)

    @Provides
    @Singleton
    fun providerSignInWithGoogleUseCase(authRepository: AuthRepository): SignInWithGoogleUseCase =
        SignInWithGoogleUseCase(authRepository)
}