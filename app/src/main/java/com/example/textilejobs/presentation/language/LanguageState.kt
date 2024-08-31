package com.example.textilejobs.presentation.language

import com.example.textilejobs.R

data class LanguageState(
    val languages: List<LanguageModel> = languageList,
    val selectedLanguage: Int = 0
)

data class LanguageModel(
    val language: String,
    val languageCode: String,
    val refImage: Int
)

val languageList = listOf(
    LanguageModel(
        languageCode = "US-en",
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