package com.example.gronurgrocery.features.auth.presentation.verification

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gronurgrocery.common.Constants.CLICK_EFFECT_DURATION
import com.example.gronurgrocery.features.auth.presentation.common.ResponseStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VerificationViewModel : ViewModel() {
    private val _state = mutableStateOf(VerificationState())
    val state: State<VerificationState> = _state

    fun updateTokenText(newToken: String) {
        _state.value = _state.value.copy(
            tokenText = newToken
        )
    }

    fun onResendButtonClicked() {
        changeResendButtonColor()
        resetTokenText()
    }

    private fun changeResendButtonColor() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                resendButtonColor = Color.Gray
            )
            delay(CLICK_EFFECT_DURATION)
            _state.value = _state.value.copy(
                resendButtonColor = Color.Black
            )
        }
    }

    private fun resetTokenText() {
        _state.value = _state.value.copy(
            tokenText = ""
        )
    }

    fun endResponse() {
        _state.value = _state.value.copy(
            verifyStatus = ResponseStatus.NONE
        )
    }

    fun isValid(): Boolean {
        return _state.value.tokenText.length == 4
    }

    fun sendVerification() {
        /* TODO */
    }
}