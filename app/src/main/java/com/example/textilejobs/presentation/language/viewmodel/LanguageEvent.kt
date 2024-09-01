package com.example.textilejobs.presentation.language.viewmodel

sealed interface LanguageEvent {
    class OnSelectionChange(val selectedLang: Int) : LanguageEvent
    class OnLanguageChange(val selectLangCode: String): LanguageEvent
}