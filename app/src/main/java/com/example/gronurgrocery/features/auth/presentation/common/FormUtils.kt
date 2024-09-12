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

fun validFullName(nameText: String): String? {
    // Check if the name contains only valid characters (letters, spaces, hyphens)
    val invalidCharRegex = Regex("[^a-zA-Z\\s-]")
    if (nameText.contains(invalidCharRegex)) {
        return "Name contains invalid characters. Only letters, spaces, and hyphens are allowed."
    }

    // Check if the name length is valid (between 2 and 50 characters)
    if (nameText.length !in 2..50) {
        return "Name must be between 2 and 50 characters long"
    }

    // If all checks pass, return null indicating the name is valid
    return null
}

fun validPhoneNumber(newPhoneNumber: String): String? {
    val phoneRegex =
        Regex("^\\+?\\d{1,3}?[-.\\s]?\\(?\\d{1,4}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$")
    if (!newPhoneNumber.matches(phoneRegex)) {
        return "Phone number is invalid"
    }
    return null
}