package com.example.gronurgrocery.features.auth.presentation.common

import android.util.Patterns

fun validEmail(emailText: String): String? {
    if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
        return "Invalid Email Address"
    }
    return null
}

fun validPassword(passwordText: String): String? {
    if (passwordText.length < 8) {
        return "Your password must be at least 8 characters long"
    }
    if (!passwordText.matches(".*[a-z].*".toRegex())) {
        return "Your password must contain at least one lowercase character"

    }
    if (!passwordText.matches(".*[A-Z].*".toRegex())) {
        return "Your password must contain at least one uppercase character"

    }
    if (!passwordText.matches(".*[0-9].*".toRegex())) {
        return "Your password must contain at least one digit"

    }
    if (!passwordText.matches(".*[@#\$%^+=*].*".toRegex())) {
        return "Your password must contain at least one special character (@,#,$,%,^,+,=,*)"

    }

    return null
}

fun validConfirmPassword(
    passwordText: String,
    confirmPasswordText: String,
): String? {
    if (passwordText != confirmPasswordText) {
        return "Passwords do not match"
    }
    return null
}