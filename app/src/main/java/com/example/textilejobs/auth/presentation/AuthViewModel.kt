package com.example.textilejobs.auth.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AuthViewModel : ViewModel() {
    private val _isCompany = MutableStateFlow(false)
    val isCompany = _isCompany.asStateFlow()

    fun changeIsCompany(newValue: Boolean) {
        _isCompany.update { newValue }
    }
}