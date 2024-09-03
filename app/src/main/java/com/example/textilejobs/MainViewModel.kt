package com.example.textilejobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textilejobs.core.constants.LocalPrefsConstants
import com.example.textilejobs.domain.usecase.GetBooleanUseCase
import com.example.textilejobs.domain.usecase.GetUserAuthKeyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getBooleanUseCase: GetBooleanUseCase,
    private val getUserAuthKeyUseCase: GetUserAuthKeyUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    private val _isLanguageChosen = MutableStateFlow(false)
    val isLanguageChosen = _isLanguageChosen.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoggedIn.value = getBooleanUseCase.invoke(LocalPrefsConstants.USER_IS_LOGGED_IN)
            _isLanguageChosen.value = getBooleanUseCase.invoke(LocalPrefsConstants.IS_LANGUAGE_CHOSEN)
            _isLoading.value = false
        }
    }
}