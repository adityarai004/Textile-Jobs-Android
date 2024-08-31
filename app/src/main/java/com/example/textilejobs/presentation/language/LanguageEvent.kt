package com.example.textilejobs.presentation.language

sealed interface LanguageEvent {
    class OnSelectionChange(val selectedLang: Int) : LanguageEvent
}