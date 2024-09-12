package com.example.gronurgrocery.features.auth.presentation.verification

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class VerificationViewModel: ViewModel() {
    private val _state = mutableStateOf(VerificationState())
    val state: State<VerificationState> = _state

    fun updateTokenText(newToken: String) {
        _state.value = _state.value.copy(
            tokenText = newToken
        )
    }
}