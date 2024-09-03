package com.example.textilejobs.core.utils

import androidx.annotation.StringRes
import com.example.textilejobs.R

data class ErrorState(
    val hasError: Boolean = false,
    @StringRes val errorMessageStringResource: Int = R.string.empty_string,
    val hasInteracted: Boolean = false
)