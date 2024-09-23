package com.example.gronurgrocery.features.profile.presentation.payment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PaymentMethodViewModel: ViewModel() {

    private val _state = mutableStateOf(PaymentMethodState())
    val state: State<PaymentMethodState> = _state

}