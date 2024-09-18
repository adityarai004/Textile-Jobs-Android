package com.example.textilejobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textilejobs.core.use_case.GetIsLanguageChosenUseCase
import com.example.textilejobs.core.use_case.GetIsUserLoggedInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getIsUserLoggedInUseCase: GetIsUserLoggedInUseCase,
    private val getIsLanguageChosenUseCase: GetIsLanguageChosenUseCase
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    private val _isLanguageChosen = MutableStateFlow(false)
    val isLanguageChosen = _isLanguageChosen.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoggedIn.value = getIsUserLoggedInUseCase()
            _isLanguageChosen.value = getIsLanguageChosenUseCase()
            _isLoading.value = false
        }
    }
}