package com.example.textilejobs.presentation.language

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LanguageViewModel: ViewModel(){
    var languageState by mutableStateOf(LanguageState())
        private set

    fun onEvent(languageEvent: LanguageEvent){
        when(languageEvent){
            is LanguageEvent.OnSelectionChange -> {
                languageState = languageState.copy(
                    selectedLanguage = languageEvent.selectedLang
                )
            }
        }
    }
}