package com.example.gronurgrocery.features.profile.presentation.address

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gronurgrocery.features.profile.domain.model.AddressType

class DeliveryAddressViewModel: ViewModel() {

    private val _state = mutableStateOf(DeliveryAddressState())
    val state: State<DeliveryAddressState> = _state

    fun updateAddressType(newAddressType: String) {
        _state.value = _state.value.copy(
            newAddress = _state.value.newAddress.copy(
                addressType = _state.value.newAddress.addressType.copy(
                    typeName = newAddressType
                )
            )
        )
    }

    fun toggleCountryMenu() {
        _state.value = _state.value.copy(
            isCountryExpanded = !_state.value.isCountryExpanded
        )
    }

    fun closeCountryMenu() {
        _state.value = _state.value.copy(
            isCountryExpanded = false
        )
    }

    fun changeCountryChoice(newCountry: String) {
        _state.value = _state.value.copy(
            newAddress = _state.value.newAddress.copy(
                country = newCountry
            )
        )
    }

    fun toggleCityMenu() {
        _state.value = _state.value.copy(
            isCityExpanded = !_state.value.isCityExpanded
        )
    }

    fun closeCityMenu() {
        _state.value = _state.value.copy(
            isCityExpanded = false
        )
    }

    fun changeCityChoice(newCity: String) {
        _state.value = _state.value.copy(
            newAddress = _state.value.newAddress.copy(
                city = newCity
            )
        )
    }
    fun updateAddressLine(newLine: String) {
        _state.value = _state.value.copy(
            newAddress = _state.value.newAddress.copy(
                addressLine = newLine
            )
        )
    }

    fun addNewAddress() {
        /* TODO() */
    }

    fun toggleBottomSheet() {
        _state.value = _state.value.copy(
            isBottomSheetOpen = !_state.value.isBottomSheetOpen
        )
    }

}