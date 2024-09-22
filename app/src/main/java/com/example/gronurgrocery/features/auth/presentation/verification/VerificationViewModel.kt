package com.example.gronurgrocery.features.auth.presentation.verification

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gronurgrocery.common.Constants
import com.example.gronurgrocery.common.Constants.CLICK_EFFECT_DURATION
import com.example.gronurgrocery.common.data.network.Resource
import com.example.gronurgrocery.features.auth.domain.use_case.ResendEmailUseCase
import com.example.gronurgrocery.features.auth.domain.use_case.SaveUserTokenUseCase
import com.example.gronurgrocery.features.auth.domain.use_case.VerifyRegisterEmailUseCase
import com.example.gronurgrocery.features.auth.presentation.common.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val verifyRegisterEmailUseCase: VerifyRegisterEmailUseCase,
    private val saveUserTokenUseCase: SaveUserTokenUseCase,
    private val resendEmailUseCase: ResendEmailUseCase
) : ViewModel() {
    private val _state = mutableStateOf(VerificationState())
    val state: State<VerificationState> = _state

    fun addEmail(email: String) {
        _state.value = _state.value.copy(
            email = email
        )
    }

    fun updateTokenText(newToken: String) {
        _state.value = _state.value.copy(
            tokenText = newToken
        )
    }

    fun onResendButtonClicked() {
        changeResendButtonColor()
        resetTokenText()
        resendEmail()
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
        viewModelScope.launch {
            verifyRegisterEmailUseCase(
                otp = _state.value.tokenText,
                email = _state.value.email
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            verifyStatus = ResponseStatus.FAILURE,
                            errorMsg = result.message
                        )
                    }
                    is Resource.Success -> {
                        if (result.data?.status == Constants.VERIFICATION_FAIL) {
                            _state.value = _state.value.copy(
                                verifyStatus = ResponseStatus.FAILURE,
                                errorMsg = result.message
                            )
                        } else {
                            _state.value = _state.value.copy(
                                verifyStatus = ResponseStatus.SUCCESS
                            )
                            saveUserToken(_state.value.tokenText)
                        }
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            verifyStatus = ResponseStatus.LOADING
                        )
                    }
                }
            }
        }
    }

    private fun resendEmail() {
        viewModelScope.launch {
            resendEmailUseCase(_state.value.email).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            resendOTPStatus = ResponseStatus.FAILURE,
                            resendOTPErrorMsg = result.message
                        )
                    }
                    is Resource.Success -> {
                        if (result.data?.status == Constants.VERIFICATION_FAIL) {
                            _state.value = _state.value.copy(
                                resendOTPStatus = ResponseStatus.FAILURE,
                                resendOTPErrorMsg = result.message
                            )
                        } else {
                            _state.value = _state.value.copy(
                                resendOTPStatus = ResponseStatus.SUCCESS
                            )
                            saveUserToken(_state.value.tokenText)
                        }
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            resendOTPStatus = ResponseStatus.LOADING
                        )
                    }
                }
            }
        }
    }

    private fun saveUserToken(
        token: String
    ) {
        viewModelScope.launch {
            saveUserTokenUseCase(token)
        }
    }
}