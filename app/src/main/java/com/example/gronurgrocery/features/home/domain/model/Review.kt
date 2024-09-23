package com.example.gronurgrocery.features.home.domain.model

data class Review(
    val user: UserBrief,
    val rating: Int,
    val date: String,
    val review: String
)