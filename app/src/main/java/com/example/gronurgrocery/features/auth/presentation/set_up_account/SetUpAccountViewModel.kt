package com.example.gronurgrocery.features.auth.presentation.set_up_account

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gronurgrocery.features.auth.domain.model.RegisterData
import com.example.gronurgrocery.features.auth.presentation.common.validConfirmPassword
import com.example.gronurgrocery.features.auth.presentation.common.validEmail
import com.example.gronurgrocery.features.auth.presentation.common.validFullName
import com.example.gronurgrocery.features.auth.presentation.common.validPassword
import com.example.gronurgrocery.features.auth.presentation.common.validPhoneNumber

class SetUpAccountViewModel: ViewModel() {

    private val _state = mutableStateOf(SetUpAccountState())
    val state: State<SetUpAccountState> = _state

    private var isFormInitialized = false

    fun updateFullNameState(newName: String) {
        _state.value = _state.value.copy(
            fullNameText = newName,
            fullNameError = validFullName(newName)
        )
    }

    fun updateEmailState(newEmail: String) {
        _state.value = _state.value.copy(
            emailText = newEmail,
            emailError = validEmail(newEmail)
        )
    }

    fun updatePhoneNumberState(newPhoneNumber: String) {
        _state.value = _state.value.copy(
            phoneNumberText = newPhoneNumber,
            phoneNumberError = validPhoneNumber(newPhoneNumber)
        )
    }

    fun updatePasswordState(newPassword: String) {
        _state.value = _state.value.copy(
            passwordText = newPassword,
            passwordError = validPassword(newPassword)
        )
    }

    fun updateConfirmPasswordState(newPassword: String) {
        _state.value = _state.value.copy(
            confirmPasswordText = newPassword,
            confirmPasswordError = validConfirmPassword(_state.value.passwordText, newPassword)
        )
    }

    fun togglePasswordVisibility() {
        _state.value = _state.value.copy(
            isPasswordVisible = !_state.value.isPasswordVisible
        )
    }

    fun toggleConfirmPasswordVisibility() {
        _state.value = _state.value.copy(
            isConfirmPasswordVisible = !_state.value.isConfirmPasswordVisible
        )
    }

    fun initializeForm(registerData: RegisterData) {
        if (!isFormInitialized) {
            updateEmailState(registerData.emailText)
            updatePasswordState(registerData.passwordText)
            updateConfirmPasswordState(registerData.confirmPasswordText)
            isFormInitialized = true
        }
    }
}