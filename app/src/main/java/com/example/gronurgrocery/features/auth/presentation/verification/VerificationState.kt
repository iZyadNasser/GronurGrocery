package com.example.gronurgrocery.features.auth.presentation.verification

import androidx.compose.ui.graphics.Color
import com.example.gronurgrocery.features.auth.presentation.common.ResponseStatus

data class VerificationState(
    val tokenText: String = "",
    val resendButtonColor: Color = Color.Black,
    val verifyStatus: ResponseStatus = ResponseStatus.NONE,
    val errorMsg: String? = "",
    val email: String = "",
    val resendOTPStatus: ResponseStatus = ResponseStatus.NONE,
    val resendOTPErrorMsg: String? = ""
)
