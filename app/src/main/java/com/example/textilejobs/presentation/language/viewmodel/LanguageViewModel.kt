package com.example.textilejobs.presentation.language.viewmodel

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textilejobs.core.constants.LocalPrefsConstants
import com.example.textilejobs.domain.usecase.SetBooleanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(private val setBooleanUseCase: SetBooleanUseCase) : ViewModel() {

    var languageState by mutableStateOf(LanguageState())
        private set

    private val _languageStateFlow = MutableStateFlow(LanguageState())
    val languageStateFlow = _languageStateFlow.asStateFlow()

    fun onEvent(languageEvent: LanguageEvent) {
        when (languageEvent) {
            is LanguageEvent.OnSelectionChange -> {
                languageState = languageState.copy(
                    selectedLanguage = languageEvent.selectedLang
                )
            }
            is LanguageEvent.OnLanguageChange -> {
                languageState = languageState.copy(
                    isLoading = true
                )
                AppCompatDelegate.setApplicationLocales(
                    LocaleListCompat.forLanguageTags(
                        languageEvent.selectLangCode
                    )
                )
                viewModelScope.launch (Dispatchers.IO){
                    setBooleanUseCase.invoke(LocalPrefsConstants.IS_LANGUAGE_CHOSEN, true)
                }
                languageState = languageState.copy(
                    isLoading = false,
                    shouldNavigate = true
                )
            }
        }
    }
}
