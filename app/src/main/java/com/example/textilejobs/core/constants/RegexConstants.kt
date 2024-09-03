package com.example.textilejobs.core.constants

object RegexConstants {
    const val EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"
    const val PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    const val NAME_REGEX = "^[a-zA-Z ]{3,}$"
    const val PHONE_REGEX = "^(\\+\\d{1,3}[-.\\s]?)?\\d{1,14}$"
    const val URL_REGEX = "^(https?:\\/\\/)?([\\da-z\\.-]+\\.)([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$"
}