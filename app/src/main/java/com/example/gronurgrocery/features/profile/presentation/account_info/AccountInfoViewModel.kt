package com.example.gronurgrocery.features.profile.presentation.account_info

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AccountInfoViewModel: ViewModel() {

    private val _state = mutableStateOf(AccountInfoState())
    val state: State<AccountInfoState> = _state

    fun updateNameState(newName: String) {
        _state.value = _state.value.copy(
            nameText = newName
        )
    }

    fun updateUsernameState(newUsername: String) {
        _state.value = _state.value.copy(
            usernameText = newUsername
        )
    }

    fun updatePhoneNumState(newNum: String) {
        _state.value = _state.value.copy(
            phoneNumText = newNum
        )
    }

    fun allDataValid(): Boolean {
        with(_state.value) {
            return ((emailError == null && nameError == null && usernameError == null && phoneNumError == null) && (emailText.isNotBlank() && nameText.isNotBlank() && usernameText.isNotBlank() && phoneNumText.isNotBlank()))
        }
    }
}