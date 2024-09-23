package com.example.gronurgrocery.features.profile.presentation.address

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DeliveryAddressViewModel: ViewModel() {

    private val _state = mutableStateOf(DeliveryAddressState())
    val state: State<DeliveryAddressState> = _state


}