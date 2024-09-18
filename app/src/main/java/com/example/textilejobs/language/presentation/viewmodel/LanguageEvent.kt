package com.example.textilejobs.language.presentation.viewmodel

sealed interface LanguageEvent {
    class OnSelectionChange(val selectedLang: Int) : LanguageEvent
    class OnLanguageChange(val selectLangCode: String): LanguageEvent
}