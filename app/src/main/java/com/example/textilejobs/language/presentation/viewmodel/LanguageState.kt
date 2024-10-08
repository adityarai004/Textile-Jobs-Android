package com.example.textilejobs.language.presentation.viewmodel

import com.example.textilejobs.R

data class LanguageState(
    val languages: List<LanguageModel> = languageList,
    val selectedLanguage: Int = 0,
    val isLoading: Boolean = false,
    val shouldNavigate : Boolean = false
)

data class LanguageModel(
    val language: String,
    val languageCode: String,
    val refImage: Int
)

val languageList = listOf(
    LanguageModel(
        languageCode = "en",
        language = "English",
        refImage = R.drawable.english_2
    ),
    LanguageModel(
        languageCode = "hi",
        language = "हिन्दी",
        refImage = R.drawable.india_gate
    ),
    LanguageModel(
        languageCode = "gu",
        language = "ગુજરાતી",
        refImage = R.drawable.dwarkadish
    )
)