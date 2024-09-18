package com.example.textilejobs.core.di

import com.example.textilejobs.MainViewModel
import com.example.textilejobs.core.use_case.GetIsLanguageChosenUseCase
import com.example.textilejobs.core.use_case.GetIsUserLoggedInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    @Singleton
    fun provideMainViewModel(
        getIsUserLoggedInUseCase: GetIsUserLoggedInUseCase,
        getIsLanguageChosenUseCase: GetIsLanguageChosenUseCase
    ): MainViewModel = MainViewModel(getIsUserLoggedInUseCase, getIsLanguageChosenUseCase)
}