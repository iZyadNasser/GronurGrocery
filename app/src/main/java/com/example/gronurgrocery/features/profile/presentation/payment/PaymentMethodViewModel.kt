package com.example.gronurgrocery.features.profile.presentation.payment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gronurgrocery.features.profile.domain.model.Payment
import com.example.gronurgrocery.features.profile.domain.model.PaymentDate

class PaymentMethodViewModel: ViewModel() {

    private val _state = mutableStateOf(PaymentMethodState())
    val state: State<PaymentMethodState> = _state

    fun toggleBottomSheet() {
        _state.value = _state.value.copy(
            isBottomSheetOpen = !_state.value.isBottomSheetOpen
        )
    }

    fun updateCardNumber(newNum: String) {
        _state.value = _state.value.copy(
            newCard = _state.value.newCard.copy(
                cardNumber = newNum
            )
        )

        checkCardNumValidation()
    }

    private fun checkCardNumValidation() {
        /* TODO() */
    }

    fun updateCardDate(newDate: String) {
        if (checkCardDateValidation(newDate)) {
            _state.value = _state.value.copy(
                newCard = _state.value.newCard.copy(
                    date = convertCardFromStringToDate(newDate)
                )
            )
        }
    }

    private fun checkCardDateValidation(cardDate: String): Boolean {
        /* TODO() */
        return true
    }

    private fun convertCardFromStringToDate(cardDate: String): PaymentDate {
        val month = cardDate.substring(0, 2)
        val year = cardDate.substring(3, cardDate.length)

        return PaymentDate(
            month = month,
            year = year
        )
    }

    fun updateCVC(newCVC: String) {
        _state.value = _state.value.copy(
            newCard = _state.value.newCard.copy(
                cvc = newCVC
            )
        )
    }

    fun addNewCard() {
        if (checkCardValidation(_state.value.newCard)) {
            _state.value = _state.value.copy(
                payments = _state.value.payments + listOf(_state.value.newCard)
            )
        }
    }

    private fun checkCardValidation(card: Payment): Boolean {
        /* TODO(Check card validation) */
        return true
    }
}