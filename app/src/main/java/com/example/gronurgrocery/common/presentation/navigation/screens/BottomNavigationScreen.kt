package com.example.gronurgrocery.common.presentation.navigation.screens

import kotlinx.serialization.Serializable

@Serializable
data class Home(
    val token: String
)

@Serializable
data class Order(
    val token: String
)

@Serializable
data class MyCart(
    val token: String
)

@Serializable
data class More(
    val token: String
)