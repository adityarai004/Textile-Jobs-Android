package com.example.textilejobs.presentation.language.viewmodel

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import java.util.Locale

class LanguageViewModel() : ViewModel() {
    var languageState by mutableStateOf(LanguageState())
        private set

    fun onEvent(languageEvent: LanguageEvent) {
        when (languageEvent) {
            is LanguageEvent.OnSelectionChange -> {
                languageState = languageState.copy(
                    selectedLanguage = languageEvent.selectedLang
                )
            }

            is LanguageEvent.OnLanguageChange -> {
                languageState = languageState.copy(isLoading = true)
                changeAppLanguage(languageEvent.selectLangCode)
                languageState = languageState.copy(isLoading = false)
            }
        }
    }

    private fun changeAppLanguage(languageCode: String) {
        val locale = Locale(languageCode)
//        Locale.setDefault(locale)

        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags("hi"))
//        val resources = getApplication(applicationContext).resources
//        val config = resources.configuration
//        config.setLocale(locale)
//        resources.updateConfiguration(config, resources.displayMetrics)
        // Optional: You can also trigger a refresh or a LiveData event to notify the UI about the language change.
    }
}

fun Context.findActivity() : Activity? = when(this){
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
