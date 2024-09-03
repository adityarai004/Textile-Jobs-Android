package com.example.textilejobs.core.di

import com.example.textilejobs.MainViewModel
import com.example.textilejobs.domain.usecase.GetBooleanUseCase
import com.example.textilejobs.domain.usecase.GetUserAuthKeyUseCase
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
        getBooleanUseCase: GetBooleanUseCase,
        getUserAuthKeyUseCase: GetUserAuthKeyUseCase
    ): MainViewModel = MainViewModel(getBooleanUseCase, getUserAuthKeyUseCase)
}