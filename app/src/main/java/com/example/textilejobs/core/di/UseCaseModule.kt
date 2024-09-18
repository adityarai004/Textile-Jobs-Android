package com.example.textilejobs.core.di

import com.example.textilejobs.auth.domain.repository.AuthRepository
import com.example.textilejobs.auth.domain.usecase.LoginUseCase
import com.example.textilejobs.auth.domain.usecase.SetIsUserLoggedInUseCase
import com.example.textilejobs.auth.domain.usecase.SetUserAuthTokenUseCase
import com.example.textilejobs.auth.domain.usecase.SignInWithGoogleUseCase
import com.example.textilejobs.auth.domain.usecase.SignUpUseCase
import com.example.textilejobs.core.data.repository.UserDataRepository
import com.example.textilejobs.core.use_case.GetIsLanguageChosenUseCase
import com.example.textilejobs.core.use_case.GetIsUserLoggedInUseCase
import com.example.textilejobs.core.use_case.GetUserAuthKeyUseCase
import com.example.textilejobs.language.domain.usecase.SetIsLanguageChosenUseCase
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
    fun provideGetUserAuthKeyUseCase(userDataRepository: UserDataRepository): GetUserAuthKeyUseCase =
        GetUserAuthKeyUseCase(userDataRepository)

    @Provides
    @Singleton
    fun provideSetUserAuthKeyUseCase(userDataRepository: UserDataRepository): SetUserAuthTokenUseCase =
        SetUserAuthTokenUseCase(userDataRepository)

    @Provides
    @Singleton
    fun provideGetIsUserLoggedInUseCase(userDataRepository: UserDataRepository): GetIsUserLoggedInUseCase =
        GetIsUserLoggedInUseCase(userDataRepository)

    @Provides
    @Singleton
    fun provideSetIsUserLoggedInUseCase(userDataRepository: UserDataRepository): SetIsUserLoggedInUseCase =
        SetIsUserLoggedInUseCase(userDataRepository)

    @Provides
    @Singleton
    fun provideGetIsLanguageChosenUseCase(userDataRepository: UserDataRepository): GetIsLanguageChosenUseCase =
        GetIsLanguageChosenUseCase(userDataRepository)

    @Provides
    @Singleton
    fun provideSetIsLanguageChosenUseCase(userDataRepository: UserDataRepository): SetIsLanguageChosenUseCase =
        SetIsLanguageChosenUseCase(userDataRepository)

    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase =
        SignUpUseCase(authRepository)

//    @Provides
//    @Singleton
//    fun provideSetIntUseCase(localPrefsRepository: LocalPrefsRepository): SetIntUseCase =
//        SetIntUseCase(localPrefsRepository)

    @Provides
    @Singleton
    fun providerSignInWithGoogleUseCase(authRepository: AuthRepository): SignInWithGoogleUseCase =
        SignInWithGoogleUseCase(authRepository)
}