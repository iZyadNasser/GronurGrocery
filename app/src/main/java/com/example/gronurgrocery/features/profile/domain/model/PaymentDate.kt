package com.example.gronurgrocery.features.profile.domain.model

data class PaymentDate(
    val month: String,
    val year: String
)

fun PaymentDate.toDateString(): String {
    var updatedMonth = month
    if (updatedMonth.length < 2) {
        updatedMonth = "0$updatedMonth"
    }

    var updatedYear = year
    if (updatedYear.length < 2) {
        updatedYear = "0$updatedYear"
    }

    return "$updatedMonth/$updatedYear"
}
