package com.example.textilejobs.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.textilejobs.domain.usecase.GetIntUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getIntUseCase: GetIntUseCase): ViewModel() {
    init {

    }
}